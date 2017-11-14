/**
 * @module gameLoop
 */

const { Player } = require('./player')
const { Ball } = require('./bodies/ball')
const { Brick, getBrickId } = require('./bodies/brick')

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle[]} paddles
 * @prop {Ball} ball
 */
exports.GameLoop = class GameLoop {
  constructor (sketch) {
    this.sketch = sketch

    // Initialise bodies
    this.ball = new Ball()
    this.players = {}
    this.bricks = {}

    this.run = this.run.bind(this)
  }

  /**
   * Update players to current state or create new players if they don't exist already
   * @method
   * @param {object[]} players
   */
  updatePlayers (players) {
    const isMultiplayer = players.length === 2
    for (const p of players) {
      if (!this.players[p.c]) {
        const isCurrentPlayer = p.c === window.wsClient.clientId
        this.players[p.c] = new Player(isCurrentPlayer, isMultiplayer)
      }
      this.players[p.c].update(p)
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
  run () {
    // Clear canvas
    this.sketch.background(0)

    for (const clientId in this.players) {
      if (!this.players.hasOwnProperty(clientId)) {
        return
      }
      const player = this.players[clientId]
      player.paddle.draw(this.sketch)
      if (player.score) {
        player.score.draw(this.sketch)
      }
    }

    for (const brickId in this.bricks) {
      if (!this.bricks.hasOwnProperty(brickId)) {
        return
      }
      const brick = this.bricks[brickId]
      if (!brick.isBroken()) {
        this.bricks[brickId].draw(this.sketch)
      }
    }

    this.ball.draw(this.sketch)
  }
}
