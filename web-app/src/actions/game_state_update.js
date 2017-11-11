/**
 * @module actions/game_state_update.js
 */

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ bodies, players }) {
    this.bodies = bodies
    this.players = players
  }

  handler () {
    if (window.gameLoop) {
      window.gameLoop.updatePlayers(this.players)
      window.gameLoop.updateBodies(this.bodies)
    }
  }
}
