/**
 * @module gameLoop
 */

const { Player, calcPlayerIndex } = require('./player')
const { Ball } = require('./bodies/ball')
const { Brick, getBrickId } = require('./bodies/brick')

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle[]} paddles
 * @prop {Ball} ball
 */
exports.GameLoop = class GameLoop {
  constructor () {
    // Initialise bodies
    this.ball = new Ball()
    this.players = []
    this.bricks = {}

    this.isMultiplayer = false

    this.run = this.run.bind(this)
  }

  /**
   * Create players objects for each player
   * @method
   * @param {number} playerCount
   */
  createPlayers (playerCount) {
    this.isMultiplayer = playerCount === 2
    for (let i = 0; i < playerCount; i++) {
      const isCurrentPlayer = i === 0
      this.players[i] = new Player(isCurrentPlayer, this.isMultiplayer)
    }
  }

  /**
   * Update paddles to current state
   * @method
   * @param {number[][]} paddles
   */
  updatePaddles (paddles) {
    for (const paddle of paddles) {
      const i = calcPlayerIndex(paddle)
      this.players[i].updatePaddle(paddles[i])
    }
  }

  /**
   * Update scores to current state
   * @method
   * @param {number[]} scores
   */
  updateScores (scores) {
    if (!this.isMultiplayer) {
      return
    }
    for (let i = 0; i < scores.length; i++) {
      this.players[i].updateScore(scores[i])
    }
  }

  /**
   * Update bricks to current state or create new bricks if they don't exist already
   * @method
   * @param {object[]} bricks
   */
  updateBricks (bricks) {
    for (const b of bricks) {
      const id = getBrickId(b)
      if (!this.bricks[id]) {
        this.bricks[id] = new Brick(id)
      }
      this.bricks[id].update(b)
    }
  }

  /**
   * Update the ball to match the server state
   * @method
   * @param {number[]} ball 
   */
  updateBall (ballObj) {
    this.ball.update(ballObj)
  }

  /**
   * Draws the current state onto the provided sketch
   * @method
   */
  run (sketch) {
    // Clear canvas
    sketch.background(0)

    for (const clientId in this.players) {
      if (!this.players.hasOwnProperty(clientId)) {
        return
      }
      const player = this.players[clientId]
      player.paddle.draw(sketch)
      if (player.score) {
        player.score.draw(sketch)
      }
    }

    for (const brickId in this.bricks) {
      if (!this.bricks.hasOwnProperty(brickId)) {
        return
      }
      const brick = this.bricks[brickId]
      if (!brick.isBroken()) {
        this.bricks[brickId].draw(sketch)
      }
    }

    this.ball.draw(sketch)
  }
}
