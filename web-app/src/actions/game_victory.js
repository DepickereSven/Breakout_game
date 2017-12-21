/**
 * @module actions/game_victory.js
 */

const { viewManager } = require('../views/index')
const backgroundMusic = require('../remember_Music')

exports.handler = function () {
  backgroundMusic.stopTheMusic()
  if (window.gameLoop && window.gameLoop.isMultiplayer) {
    viewManager.go('multiplayer_game_won.html')
  } else {
    viewManager.go('singleplayer_game_won.html')
  }
}
