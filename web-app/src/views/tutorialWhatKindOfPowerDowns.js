
const path = 'whatKindOfPowerDown.html'
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
    if (constants.IS_TOUCH_SCREEN) {
      this.viewManager.go('whereToSeeThePowerUpsAndPowerDownsPhone.html')
    } else {
      this.viewManager.go('whereToSeeThePowerUpsAndPowerDownsPc.html')
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
