const {
  CreateMultiplayerRequestAction
} = require('../actions/create_multiplayer_request')

const path = 'create.html'
exports.path = path

exports.CreateMultiplayerScreenView = class CreateMultiplayerScreenView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.createGameBtn = 'a.create_a_private_game'
  }

  createMultiplayerHandler () {
    window.wsClient.send(new CreateMultiplayerRequestAction())
  }

  onLoad () {
    $(this.createGameBtn).on('click', this.multiplayerClickHandler)
  }

  onUnload () {
    $(this.createGameBtn).off('click')
  }
}
