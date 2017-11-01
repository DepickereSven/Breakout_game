/**
 * @module actions/create_game_success.js
 */

const createdGameView = require('../views/created_game')

exports.CreateGameSuccessAction = class CreateGameSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {
    createdGameView.show(this.key)
  }
}
