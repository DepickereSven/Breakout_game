const JoinPublicGameRequestAction = require('../actions/join_public_game_request')
const CancelMultiplayerRequestAction = require('../actions/cancel_multiplayer_request')

const path = 'join_public.html'
exports.path = path

exports.view = class JoinPublic {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.handleBack = this.handleBack.bind(this)
  }

  handleBack () {
    window.wsClient.send(CancelMultiplayerRequestAction.create())
  }

  onLoad () {
    window.addEventListener('back', this.handleBack)
    window.wsClient.send(JoinPublicGameRequestAction.create())
  }

  onUnload () {
    window.removeEventListener('back', this.handleBack)
  }
}
