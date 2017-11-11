const {
  CreateMultiplayerRequestAction
} = require('../actions/create_multiplayer_request')

const path = 'create_private_public_menu.html'
exports.path = path

exports.view = class CreatePrivatePublicMenu {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.createPrivateGameBtn = 'a.create_a_private_game'
  }

  multiplayerClickHandler () {
    window.wsClient.send(new CreateMultiplayerRequestAction())
  }

  onLoad () {
    $(this.createPrivateGameBtn).on('click', this.multiplayerClickHandler)
  }

  onUnload () {
    $(this.createPrivateGameBtn).off('click')
  }
}
