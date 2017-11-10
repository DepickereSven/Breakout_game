/**
 * @module actions/create_game_success.js
 */

const { viewManager } = require('../views/index')

exports.CreateMultiplayerSuccessAction = class CreateMultiplayerSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {
    //viewManager.go('')
  }
}
