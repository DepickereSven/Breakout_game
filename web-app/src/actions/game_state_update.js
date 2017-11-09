/**
 * @module actions/game_state_update.js
 */

const { gameLoop } = require('../gameloop')

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ ball, bricks, players }) {
    this.ball = ball
    this.bricks = bricks
    this.players = players
  }

  handler () {
    gameLoop.updateBall(this.ball)
    gameLoop.updateBricks(this.bricks)
    gameLoop.updatePlayers(this.players)
    gameLoop.run()
  }
}
