/**
 * @module gameLoop
 */

const constants = require('./constants')
const utils = require('./utils')

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle} paddle
 * @prop {Ball} ball
 */
exports.GameLoop = class GameLoop {
  constructor() {
    // Initialise bodies
    this.paddle = new Paddle()
    this.ball = new Ball()
  }


  /**
   * Draws the current state onto the provided sketch
   * @method
   * @param {Sketch} s - p5.js sketch object to draw on
   */
  run(s) {
    // Clear canvas
    s.background(0)
    s.fill(255)

    // Paddle controls
    {
      this.paddle.draw(s)
    }

    // Ball controls
    {
      this.ball.draw(s)
    }

  }
}
