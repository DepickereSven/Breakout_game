/** @module player */

const { Paddle } = require('./bodies/paddle')
const { Score } = require('./bodies/score')

exports.Player = class Player {
  constructor (currentPlayer = false) {
    this.currentPlayer = currentPlayer
    this.paddle = new Paddle()
    this.score = new Score(currentPlayer)
  }

  update ({ paddle, score }) {
    this.paddle.update(paddle)
    this.score.update(score)
  }
}
