/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')

exports.handler = function ({ ball, bricks, players, countDown, time }) {
  const view = viewManager.getCurrent()
  if (view.setCount) {
    view.setCount(countDown)
    view.setTime(time)
  }
  if (window.gameLoop) {
    window.gameLoop.updateBall(ball)
    window.gameLoop.updateBricks(bricks)
    window.gameLoop.updatePlayers(players)
    window.gameLoop.run()
  }
}
