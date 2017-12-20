const path = 'ballControl_tutorial.html'
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
    if (constants.IS_TOUCH_SCREEN()) {
      this.viewManager.go('controlPaddlePhone_tutorial.html')
    } else {
      this.viewManager.go('controlPaddlePc_tutorial.html')
    }
  }
  onLoad () {
    if (constants.IS_TOUCH_SCREEN()) {
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
