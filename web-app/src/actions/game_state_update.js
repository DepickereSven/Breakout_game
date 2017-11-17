/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const playerReadyAction = require('./player_ready')
const music = require('../music')

exports.handler = function ({ b, br, p, c, tm }) {
  const view = viewManager.getCurrent()
  if (view.setCount) {
    view.setCount(c)
    view.setTime(tm)
  }

  if (c === 0 && br.length > 0) {
    music.play('brickHit')
  }

  window.gameLoop.updateBricks(br)
  window.gameLoop.updateBall(b)
  window.gameLoop.updatePlayers(p)

  if (c > 0 && br.length > 0) {
    // Wait for first 2 frames to be drawn
    requestAnimationFrame(function () {
      requestAnimationFrame(function () {
        window.wsClient.send(playerReadyAction.create())
      })
    })
  }
}
