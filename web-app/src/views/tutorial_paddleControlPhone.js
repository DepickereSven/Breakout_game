const path = 'controlPaddlePhone_tutorial.html'
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
    this.viewManager.go('powerUpsPowerDowns_tutorial.html')
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
