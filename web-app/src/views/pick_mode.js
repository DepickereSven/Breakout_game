const constants = require('../constants')

const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.logoutFromGooglePlay = '.btn_from_google'
    this.logout = this.logout.bind(this)
  }

  logout () {
    window.user = undefined

    window.wsClient.reset()

    if (!constants.IS_ANDROID_APP) {
      const auth2 = gapi.auth2.getAuthInstance()
      auth2.signOut()
    } else {
      SmashIt.logoutInAndroid()
    }

    this.viewManager.go('login.html')
  }

  onLoad () {
    $(this.logoutFromGooglePlay).on('click', this.logout)
  }

  onUnload () {
    $(this.logoutFromGooglePlay).off('click')
  }
}
