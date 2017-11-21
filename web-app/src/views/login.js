const UserLoginRequestAction = require('../actions/user_login_request')

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
  window.viewManager.go('loading.html')
  getCountryCode(country => {
    window.wsClient.send(UserLoginRequestAction.create({ token, country }))
  })
}
window.onAndroidSignIn = handleSignIn

exports.view = class LoginView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.handleWebSignIn = this.handleWebSignIn.bind(this)
  }

  handleWebSignIn (googleUser) {
    handleSignIn(googleUser.getAuthResponse().id_token)
  }
  onLoad () {
    window.onWebSignIn = this.handleWebSignIn
  }

  onUnload () {
    window.onWebSignIn = undefined
  }
}
