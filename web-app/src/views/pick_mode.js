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
  }
  logout () {
    if (!constants.IS_ANDROID_APP) {
      // do some browser stuff
    } else {
      if ($('.google_status').html() === 'Sign out:') {
        SmashIt.logoutInAndroid()
      } else {
        SmashIt.logInToAndroid()
      }
    }
  }
  changeLogInStatusForAndroid (isLoggedOut) {
    if (isLoggedOut) {
      $('.google_status').html('Sign in:')
    } else {
      $('.google_status').html('Sign out:')
    }
  }

  onLoad () {
    window.changeStatusForGoogle = this.changeLogInStatusForAndroid
    $(this.logoutFromGooglePlay).on('click', this.logout)
  }

  onUnload () {
  }
}
