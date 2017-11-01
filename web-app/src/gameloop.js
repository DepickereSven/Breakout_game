/**
 * @module gameLoop
 */

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')
const { sketch } = require('./sketch')

/**
 * @param {string} str 
 */
const firstLetterToLowerCase = str => str[0].toLowerCase() + str.slice(1)

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle} paddle
 * @prop {Ball} ball
 */
class GameLoop {
  constructor () {
    this.reset()
  }

  reset () {
    // Initialise bodies
    this.paddle = new Paddle()
    this.ball = new Ball()
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object} bodyObj 
   */
  update (bodyObj) {
    const instanceKey = firstLetterToLowerCase(bodyObj.type)
    if (this[instanceKey]) {
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
    sketch.background(0)

    this.paddle.draw(sketch)
    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()
