const {
  CreateSingleplayerRequestAction
} = require('../actions/create_singleplayer_request')

const path = 'singleplayer_game_won.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.home = '#home'
    this.next = '#next_singleplayer'
  }

  handleLevelClick () {
    window.wsClient.send(new CreateSingleplayerRequestAction())
  }
  goHome () {
    this.viewManager.goHome()
  }
  onLoad () {
    $(this.home).on('click', this.goHome.bind(this))
    $(this.next).on('click', this.handleLevelClick)
  }

  onUnload () {
  }
}
