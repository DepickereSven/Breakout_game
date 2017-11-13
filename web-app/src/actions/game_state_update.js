/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')

exports.handler = function ({ ball, bricks, players, countDown, time }) {
  const view = viewManager.getCurrent()
  if (view.countDown) {
    view.countDown(countDown)
  }
  if (window.gameLoop) {
    window.gameLoop.updateBall(ball)
    window.gameLoop.updateBricks(bricks)
    window.gameLoop.updatePlayers(players)
    window.gameLoop.run()
  }
}
