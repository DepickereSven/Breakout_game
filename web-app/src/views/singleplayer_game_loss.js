const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')

const path = 'singleplayer_game_lost.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.retry = '#retry_singleplayer'
    this.home = '#home'
  }

  handleLevelClick () {
    window.wsClient.send(CreateSingleplayerRequestAction.create())
  }
  goHome () {
    this.viewManager.goHome()
  }
  onLoad () {
    $(this.retry).on('click', this.handleLevelClick)
    $(this.home).on('click', this.goHome.bind(this))
  }

  onUnload () {}
}
