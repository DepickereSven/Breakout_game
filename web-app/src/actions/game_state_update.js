/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const music = require('../music')

exports.handler = function ({ bl, br, pl, cd, tm }) {
  const view = viewManager.getCurrent()
  if (view.setCount) {
    view.setCount(cd)
    view.setTime(tm)
  }
  if (window.gameLoop) {
    if (cd === 0 && br.length > 0) {
      music.playMusic(1)
    }

    window.gameLoop.updateBall(bl)
    window.gameLoop.updateBricks(br)
    window.gameLoop.updatePlayers(pl)
    requestAnimationFrame(window.gameLoop.run)
  }
}
