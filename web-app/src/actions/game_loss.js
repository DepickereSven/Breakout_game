/**
 * @module actions/game_loss.js
 */

const gameLossView = require('../views/game_loss')

exports.GameLossAction = class GameLossAction {
  handler () {
    gameLossView.show()
  }
}
