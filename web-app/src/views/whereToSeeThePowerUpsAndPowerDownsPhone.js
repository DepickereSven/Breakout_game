const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')
const state = require('../global_state.js')

const path = 'whereToSeeThePowerUpsAndPowerDownsPhone.html'
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
      this.startFirstLevel()
    } else {
      this.viewManager.goHome()
    }
  }

  startFirstLevel () {
    this.viewManager.go('loading.html')
    state.set('currentLevel', 1)
    window.wsClient.send(CreateSingleplayerRequestAction.create(1))
    state.set('tutorial', true)
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
