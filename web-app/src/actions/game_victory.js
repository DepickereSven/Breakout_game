/**
 * @module actions/game_victory.js
 */

const { viewManager } = require('../views/index')

exports.handler = function () {
  if (window.gameLoop && window.gameLoop.isMultiplayer) {
    viewManager.go('multiplayer_game_won.html')
  } else {
    viewManager.go('singleplayer_game_won.html')
  }
}
