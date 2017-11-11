/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ ball, bricks, players, countDown }) {
    this.ball = ball
    this.bricks = bricks
    this.players = players
    this.countDown = countDown
  }

  handler () {
    const view = viewManager.getCurrent()
    if (view.countDown) {
      view.countDown(this.countDown)
    }
    if (window.gameLoop) {
      window.gameLoop.updateBall(this.ball)
      window.gameLoop.updateBricks(this.bricks)
      window.gameLoop.updatePlayers(this.players)
      window.gameLoop.run()
    }
  }
}
