const constants = require('../constants')

const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.logoutFromGooglePlay = '.btn_from_google'
  }
  logout () {
    if (!constants.IS_ANDROID_APP) {
      // do some browser stuff
    } else {
      SmashIt.logoutInAndroid()
    }
  }
  onLoad () {
    $(this.logoutFromGooglePlay).on('click', this.logout)
  }

  onUnload () {
  }
}
