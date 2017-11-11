/**
 * @module actions/game_start.js
 */

const { viewManager } = require('../views/index')

exports.GameStartAction = class GameStartAction {
  handler () {
    viewManager.go('game.html')
  }
}
