const constants = require('../constants')
const state = require('../global_state')

const login = require('./login')
const loading = require('./loading')
const offline = require('./offline')
const pickMode = require('./pick_mode')
const multiplayerMenu = require('./multiplayer_menu')
const createPrivateSuccess = require('./create_private_success')
const joinPrivate = require('./join_private')
const joinPublic = require('./join_public')
const game = require('./game')
const singleplayerMenu = require('./singleplayer_menu')
const singleplayerLevelMenu = require('./singleplayer_level_menu')
const singleplayerScore = require('./singeplayer_score')
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
const credits = require('./credits_content')
const tutorial = require('./tutorial')
const tutorialBall = require('./tutorial-ball')
const tutorialBlock = require('./block_tutorial')
const tutorialHowTo = require('./tutorial_howTo')
const tutorialBallControl = require('./tutorial_ballControl')
const tutorialPaddleControlPc = require('./tutorial_paddleContolPc')
const tutorialPaddleControlPhone = require('./tutorial_paddleControlPhone')
const tutorialPowerUpsPowerDownTutorial = require('./tutorial_PowerUpsPowerDowns')

const viewsMap = {}
const views = [
  login,
  offline,
  loading,
  pickMode,
  multiplayerMenu,
  createPrivateSuccess,
  game,
  joinPrivate,
  joinPublic,
  singleplayerMenu,
  singleplayerLevelMenu,
  singleplayerScore,
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
  privacyPolicy,
  credits,
  tutorial,
  tutorialBall,
  tutorialBlock,
  tutorialHowTo,
  tutorialBallControl,
  tutorialPaddleControlPc,
  tutorialPaddleControlPhone,
  tutorialPowerUpsPowerDownTutorial
]
views.forEach(function (val) {
  viewsMap[val.path] = val.view
})

const hashHistory = []
let viewStack = []
const event = new CustomEvent('back')

const getPreviousView = () => viewStack[viewStack.length - 2]
const getCurrentView = () => viewStack[viewStack.length - 1]

const getPreviousHash = () => hashHistory[hashHistory.length - 2]
const getCurrentHash = () => hashHistory[hashHistory.length - 1]

const isValidPath = path => !!viewsMap[path]

function correctCurrentHash () {
  window.location.hash = getCurrentHash()
}

function onLocationChange () {
  const hash = window.location.hash
  const path = hash.slice(2) + '.html'
  if (!hash.length) {
    return
  }
  if (!isValidPath(path)) {
    correctCurrentHash()
    return
  }

  if (hash === getCurrentHash()) {
    return
  }
  if (hash === getPreviousHash()) {
    goBack(path)
    return
  }

  go(path)
}

function goBack (path) {
  let prevView = getPreviousView()
  if (!prevView || path !== prevView.path) {
    correctCurrentHash()
    return
  }

  viewStack.pop()
  hashHistory.pop()

  window.dispatchEvent(event)
  prevView.onUnload()
  slideScreenOut()
  getCurrentView().onLoad()
}

function clearViews () {
  viewStack = []
  $('.screen').remove()
}

function goHome () {
  clearViews()
  if (state.get('tutorial') === false) {
    go('tutorial.html')
  } else {
    go('modes.html')
  }
}

function goLogin () {
  clearViews()
  $('.screen').remove()
  go('login.html')
}

function go (path, params = {}, callback = () => {}) {
  const ViewConstructor = viewsMap[path]
  if (!ViewConstructor) {
    throw new Error(`View "${path}" doesn't exist.`)
  }
  const view = new ViewConstructor(viewManager, params)

  if (!view.allowUnAuth && !window.user) {
    correctCurrentHash()
    return
  }

  const currentView = getCurrentView()
  const currentScreenEl = $('.screen').last()
  if (currentView) {
    currentView.onUnload()
    if (currentView && currentView.remove) {
      viewStack.pop()
    }
  }

  const hash = '#/' + path.replace('.html', '')
  hashHistory.push(hash)
  viewStack.push(view)
  window.location.hash = hash

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
  $.ajax(url)
    .done(html => {
      htmlCache[url] = html
      callback(html)
    })
}

// Precache pages
setTimeout(() => {
  getHtml('offline.html')
  getHtml('game.html')
}, 1000)

const viewManager = window.viewManager = {
  getPreviousView,
  getCurrentView,
  goHome,
  goLogin,
  go,
  onLocationChange
}

exports.viewManager = viewManager
