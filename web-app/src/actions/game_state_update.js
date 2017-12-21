/**
 * @module actions/game_state_update.js
 */

const { viewManager } = require('../views/index')
const playerReadyAction = require('./player_ready')
const music = require('../music')
const backgroundMusic = require('../remember_Music')

// Default arguments get created at every run, so lets not make 4 new placeholder arrays everytime
const ARR = []

exports.handler = function ({ b = ARR, br = ARR, p = ARR, s = ARR, pw, c = 0, t }) {
  const view = viewManager.getCurrentView()
  if (view.setCount) {
    view.setCount(c)
  }
  if (view.setTime && t !== undefined) {
    view.setTime(t)
  }
  if (view.setPowers && pw !== undefined) {
    view.setPowers(pw)
  }
  if (c === 0 && br.length > 0) {
    music.fxSound('brickHit')
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
    backgroundMusic.playMusic()
    // Wait for first 2 frames to be drawn
    requestAnimationFrame(function () {
      requestAnimationFrame(function () {
        window.wsClient.send(playerReadyAction.create())
      })
    })
  }
}
