const pickMode = require('./pick_mode')
const multiplayerMenu = require('./multiplayer_menu')
const createPrivatePublicMenu = require('./create_private_public_menu')
const createPrivateSuccess = require('./create_private_success')
const joinPrivatePublicMenu = require('./join_private_public_menu')
const joinPrivate = require('./join_private')
const game = require('./game')

const views = [
  pickMode,
  multiplayerMenu,
  createPrivatePublicMenu,
  createPrivateSuccess,
  joinPrivatePublicMenu,
  game,
  joinPrivate
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

    const currentView = this.viewHistory[this.viewHistory.length - 1]
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
      const currentView = this.viewHistory[this.viewHistory.length - 1]
      if (currentView) {
        currentView.onUnload()
      }
      $('.screen').removeClass('currentScreen')
      $(document.body).append(
        `<div class="screen">${this.headerHtml}${html}</div>`
      )

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
