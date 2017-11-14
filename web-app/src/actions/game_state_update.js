/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const music = require('../music')

exports.handler = function ({ ball, bricks, players, countDown, time }) {
  const view = viewManager.getCurrent()
  if (view.setCount) {
    view.setCount(countDown)
    view.setTime(time)
  }
  if (window.gameLoop) {
    if (countDown === 0 && bricks.length > 0) {
      music.playMusic(1)
    }

    window.gameLoop.updateBall(ball)
    window.gameLoop.updateBricks(bricks)
    window.gameLoop.updatePlayers(players)
    requestAnimationFrame(window.gameLoop.run)
  }
}
