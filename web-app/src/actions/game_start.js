/**
 * @module actions/game_start.js
 */

const gameStartedView = require('../views/game_started')

exports.GameStartAction = class GameStartAction {
  handler () {
    gameStartedView.show()
  }
}
