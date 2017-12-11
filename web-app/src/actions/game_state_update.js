/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const playerReadyAction = require('./player_ready')
const music = require('../music')

// Default arguments get created at every run, so lets not make 4 new arrays everytime
const ARR = []

exports.handler = function ({ b = ARR, br = ARR, p = ARR, s = ARR, c = 0, tm }) {
  const view = viewManager.getCurrentView()
  if (view.setCount) {
    view.setCount(c)

    if (tm !== undefined) {
      view.setTime(tm)
    }
  }

  if (c === 0 && br.length > 0) {
    music.play('brickHit')
  }

  const isInitRun = c > 0 && br.length > 0

  window.gameLoop.updateBricks(br)
  window.gameLoop.updateBall(b)

  if (isInitRun) {
    window.gameLoop.createPlayers(p.length)
  }
  window.gameLoop.updatePaddles(p)
  window.gameLoop.updateScores(s)

  if (isInitRun) {
    // Wait for first 2 frames to be drawn
    requestAnimationFrame(function () {
      requestAnimationFrame(function () {
        window.wsClient.send(playerReadyAction.create())
      })
    })
  }
}
