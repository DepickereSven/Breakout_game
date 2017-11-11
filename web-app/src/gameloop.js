/**
 * @module gameLoop
 */

const { Player } = require('./player')
const { Ball } = require('./bodies/ball')

/**
 * @param {string} str 
 */
const firstLetterToLowerCase = str => str[0].toLowerCase() + str.slice(1)

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
    this.players = []
    this.ball = new Ball()
  }

  updatePlayers (players) {
    if (this.players.length === 0) {
      this.players = players.map(function (item, index) {
        return new Player(index === 0, players.length === 2)
      })
    }

    for (let i = 0; i < this.players.length; i++) {
      this.players[i].update(players[i])
    }
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object[]} bodyObj 
   */
  updateBodies (bodies) {
    for (const bodyObj of bodies) {
      const instanceKey = firstLetterToLowerCase(bodyObj.type)
      this[instanceKey].update(bodyObj)
    }

    this.run()
  }

  /**
   * Draws the current state onto the provided sketch
   * @method
   */
  run () {
    // Clear canvas
    this.sketch.background(0)

    for (const player of this.players) {
      player.paddle.draw(this.sketch)
      if (player.score) {
        player.score.draw(this.sketch)
      }
    }

    this.ball.draw(this.sketch)
  }
}