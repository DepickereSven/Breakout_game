const pickMode = require('./pick_mode')
const multiplayerMenu = require('./multiplayer_menu')
const createPrivateSuccess = require('./create_private_success')
const joinPrivate = require('./join_private')
const joinPublic = require('./join_public')
const game = require('./game')
const singleplayerMenu = require('./singleplayer_menu')
const singleplayerLevelMenu = require('./singleplayer_level_menu')

const views = [
  pickMode,
  multiplayerMenu,
  createPrivateSuccess,
  game,
  joinPrivate,
  joinPublic,
  singleplayerMenu,
  singleplayerLevelMenu
]

const viewsMap = {}
views.forEach(function (val) {
  viewsMap[val.path] = val.view
})

exports.viewsMap = viewsMap

class ViewManager {
  constructor () {
    this.viewHistory = []
    this.headerHtml = ''

    this.getHeader()

    this.onLocationChange = this.onLocationChange.bind(this)
    this.getCurrent = this.getCurrent.bind(this)
  }

  getCurrent () {
    return this.viewHistory[this.viewHistory.length - 1]
  }

  getHeader () {
    $.ajax({
      url: 'header.html'
    }).done(html => {
      this.headerHtml = html
    })
  }

  onLocationChange () {
    const hash = window.location.hash
    if (!hash.length) {
      return
    }

    const path = hash.slice(2) + '.html'

    const currentView = this.getCurrent()
    if (!currentView || currentView.path === path) {
      return
    }

    this.go(path)
  }

  go (path, params = {}) {
    const ViewConstructor = viewsMap[path]

    if (!ViewConstructor) {
      throw new Error(`View "${path}" doesn't exist.`)
    }

    const view = new ViewConstructor(this)

    $.ajax({
      url: path
    }).done(html => {
      const currentView = this.getCurrent()
      if (currentView) {
        currentView.onUnload()
      }
      $('.screen').removeClass('currentScreen')

      const header = view.hideHeader ? '' : this.headerHtml
      $(document.body).append(`<div class="screen">${header}${html}</div>`)

      view.onLoad(params)

      this.viewHistory.push(view)

      window.location.hash = '/' + path.replace('.html', '')

      setTimeout(function () {
        $('.screen')
          .last()
          .addClass('currentScreen')
        $('.screen:not(.currentScreen)').remove()
      }, 100)
    })
  }
}

const viewManager = new ViewManager()
window.onhashchange = viewManager.onLocationChange

exports.viewManager = viewManager
