const UserLoginRequestAction = require('../actions/user_login_request')
const constants = require('../constants')

const path = 'login.html'
exports.path = path

function getCountryCode (callback) {
  $.getJSON('http://ip-api.com/json', function (data) {
    if (data) {
      callback(data.countryCode)
    }
  })
}

function handleSignIn (token) {
  localStorage.setItem('SignedIn', JSON.stringify(true))
  window.viewManager.go('loading.html')
  getCountryCode(country => {
    window.wsClient.send(UserLoginRequestAction.create({ token, country }))
  })
}
window.onAndroidSignIn = handleSignIn

function renderSignIn () {
  const elId = 'signin'

  gapi.signin2.render(elId, {
    scope: 'openid',
    width: 200,
    height: 40,
    longtitle: true,
    theme: 'dark',
    onsuccess: handleSuccess,
    onfailure: handleFailure
  })

  if (constants.IS_ANDROID_APP) {
    setTimeout(() => {
      const el = document.getElementById(elId)
      $(el).replaceWith($(el).clone())
      $('#' + elId).click(SmashIt.logInToAndroid)
    }, 200)
  }
}
window.renderSignIn = renderSignIn

function handleSuccess (googleUser) {
  handleSignIn(googleUser.getAuthResponse().id_token)
}

function handleFailure () {
  throw new Error('failed google sign in')
}

exports.view = class LoginView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
  }

  onLoad () {
  }

  onUnload () {
  }
}
