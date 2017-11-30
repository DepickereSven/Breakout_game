const constants = require('../constants')

const path = 'login.html'
exports.path = path

exports.view = class LoginView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.signInBtn = '#signin-btn'
  }

  onLoad () {
    if (constants.IS_ANDROID_APP) {
      $(this.signInBtn).on('click', function (e) {
        e.preventDefault()
        SmashIt.logInToAndroid()
      })
      return
    }

    const redirectUri = window.location.origin + window.location.pathname
    let href = 'https://accounts.google.com/o/oauth2/auth'
    href += `?redirect_uri=${redirectUri}`
    href += '&response_type=token'
    href += `&client_id=${constants.G_CLIENT_ID}`
    href += '&scope=profile email'
    href += '&fetch_basic_profile=true'
    href += '&approval_prompt=auto'
    href += '&access_type=online'
    href = encodeURI(href)

    $(this.signInBtn).attr('href', href)
  }

  onUnload () {
    if (constants.IS_ANDROID_APP) {
      $(this.signInBtn).off('click')
    }
  }
}
