/**
 * @module actions/create_game_success.js
 */

const createdGameView = require('../views/created_game')

exports.CreateMultiplayerSuccessAction = class CreateMultiplayerSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {
    createdGameView.show(this.key)
  }
}
