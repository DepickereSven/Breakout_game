const CreateMultiplayerRequestAction = require('../actions/create_multiplayer_request')

const path = 'multiplayer_menu.html'
exports.path = path

exports.view = class MultiplayerMenuView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.createPrivateGameBtn = '.create_a_private_game'
  }

  multiplayerClickHandler () {
    window.wsClient.send(CreateMultiplayerRequestAction.create())
  }

  onLoad () {
    $(this.createPrivateGameBtn).on('click', this.multiplayerClickHandler)
  }

  onUnload () {
    $(this.createPrivateGameBtn).off('click')
  }
}
