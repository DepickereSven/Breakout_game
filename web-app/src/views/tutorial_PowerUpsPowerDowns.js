const state = require('../global_state.js')

const path = 'powerUpsPowerDowns_tutorial.html'
exports.path = path

const constants = require('../constants')

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.nextPage = this.nextPage.bind(this)
  }
  nextPage (e) {
    if (state.get('tutorial') === undefined) {
      this.viewManager.go('powerUpExplanation.html')
    } else {
      this.viewManager.goHome()
    }
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
