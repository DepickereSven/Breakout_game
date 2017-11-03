/**
 * @module gameLoop
 */

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')
const { sketch } = require('./sketch')
const { MovePaddleLeftAction } = require('./actions/move_paddle_left')
const { MovePaddleRightAction } = require('./actions/move_paddle_right')

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
class GameLoop {
  constructor () {
    this.reset()
  }

  reset () {
    // Initialise bodies
    this.paddles = [new Paddle(), new Paddle()]
    this.ball = new Ball()
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object[]} bodyObj 
   */
  update (bodies) {
    let paddleIndex = 0
    for (const bodyObj of bodies) {
      const instanceKey = firstLetterToLowerCase(bodyObj.type)
      if (instanceKey === 'paddle') {
        this.paddles[paddleIndex].update(bodyObj)
        paddleIndex++
      } else if (this[instanceKey]) {
        this[instanceKey].update(bodyObj)
      }
    }

    if (sketch.keyIsPressed) {
      switch (sketch.keyCode) {
        case 37:
          window.wsClient.send(new MovePaddleLeftAction())
          break
        case 39:
          window.wsClient.send(new MovePaddleRightAction())
          break
      }
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

    for (const paddle of this.paddles) {
      paddle.draw(sketch)
    }
    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()
