/**
 * @module actions/game_state_update.js
 */

const { gameLoop } = require('../gameloop')

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ bodies }) {
    this.bodies = bodies
  }

  handler () {
    gameLoop.update(this.bodies)
  }
}
