const { signOut } = require('../user')

const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.logoutFromGooglePlay = '.btn_from_google'
  }

  onLoad () {
    $(this.logoutFromGooglePlay).on('click', signOut)
  }

  onUnload () {
    $(this.logoutFromGooglePlay).off('click')
  }
}
