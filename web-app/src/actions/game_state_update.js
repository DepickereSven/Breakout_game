/**
 * @module actions/game_state_update.js
 */

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ ball, bricks, players }) {
    this.ball = ball
    this.bricks = bricks
    this.players = players
  }

  handler () {
    if (window.gameLoop) {
      window.gameLoop.updateBall(this.ball)
      window.gameLoop.updateBricks(this.bricks)
      window.gameLoop.updatePlayers(this.players)
      window.gameLoop.run()
    }
  }
}
