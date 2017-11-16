/**
 * @module initialize
 */

const { wsClient } = require('./socket/client')
const constants = require('./constants')

// require('./music')
// require('./stats')
// require('./get_nation_data')
// require('./generate_levels')

const { viewManager } = require('./views/index')

// Views
$(document).ready(function () {
  window.wsClient = wsClient
  wsClient.open()
  viewManager.goHome()

  /**
 * - The android app needs 4,5 sec to show the vid.
 * - The body needs to be set to the full height of the browser (vh is not supported in webview)
 * - Fade in the body
 */
  const timeout = constants.IS_ANDROID_APP ? 4500 : 0
  setTimeout(function () {
    $('body').css('height', window.innerHeight)
    $('#start').addClass('load')
  }, timeout)
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
