/**
 * @module bodies/brick
 */

const utils = require('../utils')

exports.getBrickId = ([x, y]) => x + ':' + y

/**
 * Represents a brick
 * @class
 * @prop {string} id
 * @prop {string} type
 * @prop {number} x - horizontal position
 * @prop {number} h - height
 * @prop {number} w - width
 * @prop {number} lives
 * @prop {number[]} color
 */
exports.Brick = class Brick {
  constructor (id) {
    this.id = id
    this.type = undefined
    this.lives = 0
    this.x = 0
    this.y = 0
    this.h = 16
    this.w = 0
    this.borderRadius = 2
    this.color = utils.randomColor()
  }

  /**
   * Update body to match the server state
   * @method
   * @param {object} bodyObj 
   */
  update ([x, y, w, type, lives]) {
    this.type = type
    this.w = w
    this.lives = lives
    this.x = x
    this.y = y
  }

  /**
   * Check if the brick is broken
   * @method
   * @return {boolean}
   */
  isBroken () {
    return this.lives < 1
  }

  /**
   * Draw the brick on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(...this.color)
    s.rect(this.x, this.y, this.w, this.h, this.borderRadius)
  }
}
