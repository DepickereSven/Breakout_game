/**
 * @module actions/game_loss.js
 */

const { viewManager } = require('../views/index')
const backgroundMusic = require('../music_controller')

exports.handler = function () {
  backgroundMusic.stopTheMusic()
  if (window.gameLoop && window.gameLoop.isMultiplayer) {
    viewManager.go('multiplayer_game_lost.html')
  } else {
    viewManager.go('singleplayer_game_lost.html')
  }
}
