/**
 * @module actions/game_loss.js
 */

// const gameLossView = require('../views/game_loss')

const { viewManager } = require('../views/index')

exports.GameLossAction = class GameLossAction {
  handler () {
    // gameLossView.show()
    viewManager.go('singleplayer_game_lost.html')
  }
}
