/**
 * @module actions/game_victory.js
 */

const { viewManager } = require('../views/index')
const backgroundMusic = require('../remember_Music')
const state = require('../global_state.js')
const updateSingleplayerScore = require('./updateSingleplayerScore')

exports.handler = function () {
  backgroundMusic.stopTheMusic()
  if (window.gameLoop && window.gameLoop.isMultiplayer) {
    viewManager.go('multiplayer_game_won.html')
  } else {
    console.log(window.gameLoop.currentTime)
    updateSingleplayerScore.updateScores(state.get('currentLevel'), window.gameLoop.currentTime)
    console.log('test')
    viewManager.go('singleplayer_game_won.html')
  }
}
