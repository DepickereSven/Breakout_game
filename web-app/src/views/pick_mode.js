const constants = require('../constants')

const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.logoutFromGooglePlay = '.btn_from_google'
    this.changeLogInStatusForAndroid = this.changeLogInStatusForAndroid.bind(this)
    this.logout = this.logout.bind(this)
  }
  logout () {
    if ($('.google_status').text() === 'Sign in:') {
      SmashIt.logInToAndroid()
      return
    }

    if (!constants.IS_ANDROID_APP) {
      const auth2 = gapi.auth2.getAuthInstance()
      auth2.signOut()
    } else {
      SmashIt.logoutInAndroid()
    }
    this.viewManager.go('login.html')
    window.wsClient.reset()
  }
  changeLogInStatusForAndroid (isLoggedOut) {
    if (isLoggedOut) {
      $('.google_status').text('Sign in:')
    } else {
      $('.google_status').text('Sign out:')
    }
  }

  onLoad () {
    window.changeStatusForGoogle = this.changeLogInStatusForAndroid
    $(this.logoutFromGooglePlay).on('click', this.logout)
  }

  onUnload () {
    $(this.logoutFromGooglePlay).off('click')
  }
}
