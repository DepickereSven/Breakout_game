const { signOut } = require('../user')
const state = require('../global_state.js')

const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager

    this.logoutFromGooglePlay = '.btn_from_google'
    this.multi = '.Multi'
    this.nextPage = this.nextPage.bind(this)
  }
  nextPage (e) {
    if (state.get('multi-tut') === undefined) {
      this.viewManager.go('tutorialMulti.html')
    } else {
      this.viewManager.go('multiplayer_menu.html')
    }
  }

  onLoad () {
    $(this.logoutFromGooglePlay).on('click', signOut)
    $(this.multi).on('click', this.nextPage)
    $(window).off('touchend')
  }

  onUnload () {
    $(this.logoutFromGooglePlay).off('click')
    $(this.multi).off('click')
  }
}
