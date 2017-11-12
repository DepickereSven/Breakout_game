/**
 * @module actions/game_victory.js
 */

// const gameVictoryView = require('../views/game_victory')

const { viewManager } = require('../views/index')

exports.GameVictoryAction = class GameVictoryAction {
  handler () {
    // gameVictoryView.show()
    viewManager.go('singleplayer_game_won.html')

  }
}
