/** @module player */

const {Paddle} = require('./bodies/paddle')
const {Score} = require('./bodies/score')

exports.Player = class Player {
  constructor (currentPlayer = false, multiplayer = true) {
    this.currentPlayer = currentPlayer
    this.paddle = new Paddle()
    this.multiplayer = multiplayer
    if (this.multiplayer) {
      this.score = new Score(currentPlayer)
    }
  }

  update ({paddle, score}) {
    this.paddle.update(paddle)
    if (this.multiplayer) {
      this.score.update(score)
    }
  }
}
