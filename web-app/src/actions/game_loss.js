/**
 * @module actions/game_loss.js
 */

const { viewManager } = require('../views/index')

exports.handler = function () {
  const mod = JSON.parse(localStorage.getItem('whatMode'))
  if (mod === 'multi') {
    viewManager.go('multiplayer_game_lost.html')
  } else {
    viewManager.go('singleplayer_game_lost.html')
  }
}
