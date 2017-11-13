const JoinPublicGameRequestAction = require('../actions/join_public_game_request')

const path = 'join_public.html'
exports.path = path

exports.view = class JoinPublic {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true
  }

  onLoad () {
    window.wsClient.send(JoinPublicGameRequestAction.create())
  }

  onUnload () {
  }
}
