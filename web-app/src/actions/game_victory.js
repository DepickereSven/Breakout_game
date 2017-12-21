/**
 * @module actions/game_victory.js
 */

const { viewManager } = require('../views/index')
const backgroundMusic = require('../remember_Music')

exports.handler = function (action) {
  backgroundMusic.stopTheMusic()

  if (window.gameLoop && window.gameLoop.isMultiplayer) {
    const points = action.points;
    viewManager.go('multiplayer_game_won.html', {points})
  } else {
    viewManager.go('singleplayer_game_won.html')
  }
}
