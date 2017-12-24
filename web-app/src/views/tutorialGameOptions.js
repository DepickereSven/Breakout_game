const path = 'infoAboutGamesMulti.html'
exports.path = path

const constants = require('../constants')
const state = require('../global_state.js')

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.nextPage = this.nextPage.bind(this)
  }
  nextPage (e) {
    state.set('multi-tut', true)
    this.viewManager.goHome()
  }
  onLoad () {
    if (constants.IS_TOUCH_SCREEN) {
      $(window).on('touchend', this.nextPage)
    } else {
      $(window).on('click', this.nextPage)
    }
  }

  onUnload () {
    $(window).off('click')
    $(window).off('touchend')
  }
}
