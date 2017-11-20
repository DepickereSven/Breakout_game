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

    this.handleWebSignIn = this.handleWebSignIn.bind(this)
    this.signIn = this.signIn.bind(this)
  }
  signIn (token) {
    this.viewManager.go('loading.html')
    let name = 'wingcrony ' + token
    console.log(token)
    console.log(name)
    getCountryCode(country => {
      window.wsClient.send(UserLoginRequestAction.create({ token, country }))
    })
  }

  handleWebSignIn (googleUser) {
    console.log(googleUser)
    console.log(googleUser.getAuthResponse().id_token)
    this.signIn(googleUser.getAuthResponse().id_token)
  }

  onLoad () {
    console.log('test')
    window.onWebSignIn = this.handleWebSignIn
    window.onAndroidSignIn = this.signIn
  }

  onUnload () {
    window.onWebSignIn = undefined
    window.onAndroidSignIn = undefined
  }
}
