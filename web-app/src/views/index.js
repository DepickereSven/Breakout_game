const login = require('./login')
const loading = require('./loading')
const pickMode = require('./pick_mode')
const multiplayerMenu = require('./multiplayer_menu')
const createPrivateSuccess = require('./create_private_success')
const joinPrivate = require('./join_private')
const joinPublic = require('./join_public')
const game = require('./game')
const singleplayerMenu = require('./singleplayer_menu')
const singleplayerLevelMenu = require('./singleplayer_level_menu')
const singleplayerWon = require('./singleplayer_game_victory')
const singleplayerLost = require('./singleplayer_game_loss')
const multiplayerWon = require('./multiplayer_game_victory')
const multiplayerLost = require('./multiplayer_game_loss')
const stats = require('./stats_for_multi')

const backUrl = [
  ['modes', 'singleplayer_menu', 'singleplayer_level_menu'],
  ['modes', 'multiplayer_menu']
]

const views = [
  login,
  loading,
  pickMode,
  multiplayerMenu,
  createPrivateSuccess,
  game,
  joinPrivate,
  joinPublic,
  singleplayerMenu,
  singleplayerLevelMenu,
  singleplayerWon,
  singleplayerLost,
  multiplayerWon,
  multiplayerLost,
  stats
]

const viewsMap = {}
views.forEach(function (val) {
  viewsMap[val.path] = val.view
})

exports.viewsMap = viewsMap

const viewsToRemove = {
  'game.html': true,
  'loading.html': true
}

const SCREEN_ANIMATION_TIME = 300

class ViewManager {
  constructor () {
    this.viewHistory = []
    this.headerHtml = ''

    this.getHeader()

    this.onLocationChange = this.onLocationChange.bind(this)
    this.getCurrent = this.getCurrent.bind(this)
    this.goHome = this.goHome.bind(this)

    this.event = new CustomEvent('back')
  }

  getPrevious () {
    return this.viewHistory[this.viewHistory.length - 2]
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

    const foundViewIndex = this.viewHistory.findIndex(v => v.path === path)

    if (foundViewIndex === this.viewHistory.length - 1) {
      return
    }

    if (foundViewIndex > -1 && foundViewIndex === this.viewHistory.length - 2) {
      this.goBack()
    } else {
      this.go(path)
    }
  }

  goBack () {
    window.dispatchEvent(this.event)
    const url = window.location.href
    if (url.indexOf('single') > 1) {
      getRightUrl(0, url)
    } else {
      getRightUrl(1, url)
    }
    this.getCurrent().onUnload()
    this.viewHistory.pop()

    // const previousView = this.getCurrent()
    // previousView.onLoad()

    const currentViewEl = $('.screen')
      .last()
      .addClass('slideOut')

    setTimeout(() => {
      currentViewEl.remove()
    }, SCREEN_ANIMATION_TIME)
  }

  goHome () {
    this.viewHistory = []
    $('.screen').remove()
    this.go('modes.html')
  }

  go (path, params = {}, callback) {
    const ViewConstructor = viewsMap[path]

    if (!ViewConstructor) {
      throw new Error(`View "${path}" doesn't exist.`)
    }

    const view = new ViewConstructor(this, params)

    const currentView = this.getCurrent()
    if (currentView) {
      currentView.onUnload()
      if (currentView && viewsToRemove[currentView.path]) {
        this.viewHistory.pop()
        $('.screen')
          .last()
          .remove()
      }
    }

    this.viewHistory.push(view)
    window.location.hash = '/' + path.replace('.html', '')

    $.ajax({ url: path }).done(html => {
      const header = view.hideHeader ? '' : this.headerHtml
      $(document.body).append(`<div class="screen">${header}${html}</div>`)
      view.onLoad()

      setTimeout(() => {
        $('.screen')
          .last()
          .addClass('slideUp')

        if (callback) {
          setTimeout(callback, SCREEN_ANIMATION_TIME)
        }
      }, 50)
    })
  }
}

function getRightUrl (index, url) {
  for (let i = 0; i < backUrl[index].length; i++) {
    if (url.indexOf(backUrl[index][i]) > 1) {
      viewManager.go(backUrl[index][i - 1] + '.html')
    }
  }
  viewManager.go(backUrl[index][backUrl.length - 1] + '.html')
}

const viewManager = new ViewManager()
window.viewManager = viewManager

exports.viewManager = viewManager
