/**
 * @module initialize
 */

require('babel-polyfill')

const {wsClient} = require('./socket/client')
const constants = require('./constants')

const {viewManager} = require('./views/index')
const {signIn, getToken} = require('./user')
const state = require('./global_state')

const initialUrl = (new URL(document.location.href))
const isLoginRedirect = /^#access_token=/i.test(initialUrl.hash)

// Views
$(document).ready(function () {
  state.rehydrate()

  if (!constants.IS_ANDROID_APP) {
    $('head').append(`<style type="text/css">.android-only { display:none; }</style>`)
  }
  $('body').css('height', window.innerHeight)

  window.wsClient = wsClient
  wsClient.open(function () {
    if (isLoginRedirect) {
      const token = decodeURI(initialUrl.hash).split('&')[0].replace('#access_token=', '')
      signIn(token)
    } else {
      const storedToken = getToken()
      if (storedToken) {
        signIn(storedToken)
      } else {
        viewManager.go('login.html')
      }
    }
  })
})

$('body').on('click', '#go-back', function (e) {
  window.history.back()
})

window.onhashchange = function (e) {
  viewManager.onLocationChange()
}

window.onpopstate = function (e) {
  e.preventDefault()
}

if (localStorage.getItem('gameSound') === null) {
  localStorage.setItem('gameSound', JSON.stringify(true))
  localStorage.setItem('fxSound', JSON.stringify(true))
}

if (localStorage.getItem('tutorial') === null) {
  localStorage.setItem('tutorial', JSON.stringify(false))
}
