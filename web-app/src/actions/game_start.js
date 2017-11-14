/**
 * @module actions/game_start.js
 */

const { viewManager } = require('../views/index')
const playerReadyAction = require('./player_ready')

exports.handler = function () {
  viewManager.go('game.html', {}, function () {
    window.wsClient.send(playerReadyAction.create())
  })
}
