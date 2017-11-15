/**
 * @module actions/game_start.js
 */

const { viewManager } = require('../views/index')
const { GameLoop } = require('../gameloop')

exports.handler = function () {
  window.gameLoop = new GameLoop()
  viewManager.go('game.html', {})
}
