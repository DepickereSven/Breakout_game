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

exports.view = class LoginView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.handleSignIn = this.handleSignIn.bind(this)
  }

  handleSignIn (googleUser) {
    getCountryCode(country => {
      window.wsClient.send(
        UserLoginRequestAction.create({
          token: googleUser.getAuthResponse().id_token,
          country
        })
      )

      this.viewManager.go('loading.html')
    })
  }

  onLoad () {
    window.onSignIn = this.handleSignIn
  }

  onUnload () {
    window.onSignIn = undefined
  }
}
