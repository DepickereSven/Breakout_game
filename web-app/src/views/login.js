const { User } = require('../user')
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
    const profile = googleUser.getBasicProfile()

    getCountryCode((country) => {
      window.user = new User({
        id: profile.getId(),
        username: profile.getName(),
        imageUrl: profile.getImageUrl(),
        email: profile.getEmail(),
        country
      })

      window.wsClient.send(UserLoginRequestAction.create(window.user))
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
