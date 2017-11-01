/**
 * @module bodies/ball
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents the ball
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} y - vertical position
 * @prop {number} dx - horizontal speed
 * @prop {number} dy - vertical speed
 */
exports.Ball = class Ball {
  constructor(){
    this.height = 0
    this.width = 0

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw(s) {
    s.fill(this.color)
    s.ellipse(this.x, this.y, this.height)
  }
}


