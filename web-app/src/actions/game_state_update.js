/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const playerReadyAction = require('./player_ready')
const music = require('../music')

let firstUpdate = true

exports.handler = function ({ bl, br, pl, cd, tm }) {
  const view = viewManager.getCurrent()
  if (view.setCount) {
    view.setCount(cd)
    view.setTime(tm)
  }

  if (!firstUpdate && br.length > 0) {
    music.play('brickHit')
  }

  window.gameLoop.updateBricks(br)
  window.gameLoop.updateBall(bl)
  window.gameLoop.updatePlayers(pl)

  if (firstUpdate) {
    firstUpdate = false

    // Wait for first 2 frames to be drawn
    requestAnimationFrame(function () {
      requestAnimationFrame(function () {
        window.wsClient.send(playerReadyAction.create())
      })
    })
  }
}
