/**
 * @module actions/game_state_update.js
 */

const { gameLoop } = require('../gameloop')

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ bodies, players }) {
    this.bodies = bodies
    this.players = players
  }

  handler () {
    gameLoop.updatePlayers(this.players)
    gameLoop.updateBodies(this.bodies)
  }
}
