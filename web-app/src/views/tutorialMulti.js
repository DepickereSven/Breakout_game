const path = 'tutorialMulti.html'
exports.path = path

const constants = require('../constants')

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.button = 'a'
    this.nextPage = this.nextPage.bind(this)
  }
  nextPage (e) {
    if (constants.IS_TOUCH_SCREEN) {
      this.viewManager.go('gameLookPhone.html')
    } else {
      this.viewManager.go('gameLookPc.html')
    }
  }
  onLoad () {
    if (constants.IS_TOUCH_SCREEN) {
      $(this.button).on('touchend', this.nextPage)
    } else {
      $(this.button).on('click', this.nextPage)
    }
  }

  onUnload () {
    $(this.button).off('click')
    $(this.button).off('touchend')
  }
}
