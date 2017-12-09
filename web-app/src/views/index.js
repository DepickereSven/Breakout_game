const constants = require('../constants')

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
const settings = require('./settings')
const soundSettings = require('./soud_setting')
const meet = require('./meet')
const gameVersion = require('./game_version')
const socialMedia = require('./social_media')
const privacyPolicy = require('./privacy_policy')

const viewsMap = {}
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
  stats,
  settings,
  soundSettings,
  meet,
  gameVersion,
  socialMedia,
  privacyPolicy
]
views.forEach(function (val) {
  viewsMap[val.path] = val.view
})

let viewHistory = []
const event = new CustomEvent('back')

const getPrevious = () => viewHistory[viewHistory.length - 2]
const getCurrent = () => viewHistory[viewHistory.length - 1]

function onLocationChange () {
  const hash = window.location.hash
  if (!hash.length) {
    return
  }
  const path = hash.slice(2) + '.html'
  const foundViewIndex = viewHistory.findIndex(v => v.path === path)
  if (foundViewIndex === viewHistory.length - 1) {
    return
  }
  if (foundViewIndex > -1 && foundViewIndex === viewHistory.length - 2) {
    goBack()
  } else {
    go(path)
  }
}

function goBack () {
  window.dispatchEvent(event)
  getCurrent().onUnload()
  viewHistory.pop()
  slideScreenOut()
  getCurrent().onLoad()
}

function goHome () {
  viewHistory = []
  $('.screen').remove()
  go('modes.html')
}

function go (path, params = {}, callback = () => {}) {
  const ViewConstructor = viewsMap[path]
  if (!ViewConstructor) {
    throw new Error(`View "${path}" doesn't exist.`)
  }
  const view = new ViewConstructor(viewManager, params)

  const currentView = getCurrent()
  const currentScreenEl = $('.screen').last()
  if (currentView) {
    currentView.onUnload()
    if (currentView && currentView.remove) {
      viewHistory.pop()
    }
  }

  viewHistory.push(view)
  window.location.hash = '/' + path.replace('.html', '')

  getHtml(path, html => {
    getHtml('header.html', headerHtml => {
      $(document.body).append(`<div class="screen">${!view.hideHeader ? headerHtml : ''}${html}</div>`)
      if (!view.hideHeader && window.user) {
        $('.header-container .points').text(window.user.smashbit)
      }
      view.onLoad()
      slideScreenIn(() => {
        if (currentView && currentView.remove && currentScreenEl) {
          currentScreenEl.remove()
        }
        callback()
      })
    })
  })
}

function slideScreenOut (callback = () => {}) {
  setTimeout(() => {
    const screen = $('.screen')
      .last()
      .removeClass('slideUp')

    setTimeout(() => {
      screen.remove()
      callback()
    }, constants.SCREEN_ANIMATION_TIME)
  }, 50)
}

function slideScreenIn (callback = () => {}) {
  setTimeout(() => {
    $('.screen')
      .last()
      .addClass('slideUp')

    setTimeout(callback, constants.SCREEN_ANIMATION_TIME)
  }, 50)
}

const htmlCache = {}
function getHtml (url, callback = () => {}) {
  const cachedHtml = htmlCache[url]
  if (cachedHtml) {
    callback(cachedHtml)
    return
  }
  $.ajax({ url })
    .done(html => {
      htmlCache[url] = html
      callback(html)
    })
}

// Precache game html
setTimeout(() => {
  getHtml('game.html')
}, 3000)

const viewManager = window.viewManager = {
  getPrevious,
  getCurrent,
  goHome,
  go,
  onLocationChange
}

exports.viewManager = viewManager
