/** @module player */

const constants = require('./constants')
const { Paddle } = require('./bodies/paddle')
const { Score } = require('./bodies/score')

exports.calcPlayerIndex = ([x, y]) => (y > constants.C_HEIGHT / 2) ? 0 : 1

exports.Player = class Player {
  constructor (currentPlayer = false, multiplayer = true) {
    this.currentPlayer = currentPlayer
    this.paddle = new Paddle()
    this.multiplayer = multiplayer
    if (this.multiplayer) {
      this.score = new Score(currentPlayer)
    }
  }

  updatePaddle (paddle) {
    this.paddle.update(paddle)
  }

  updateScore (score) {
    this.score.update(score)
  }
}
