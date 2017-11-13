/**
 * @module actions/game_start.js
 */

const { viewManager } = require('../views/index')

exports.handler = function () {
  viewManager.go('game.html')
}
