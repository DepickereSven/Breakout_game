/**
 * @module actions/game_victory.js
 */

const gameVictoryView = require('../views/game_victory')

exports.GameVictoryAction = class GameVictoryAction {
  handler () {
    gameVictoryView.show()
  }
}
