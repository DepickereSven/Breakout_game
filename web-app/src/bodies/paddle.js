/**
 * @module bodies/paddle
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents paddle
 * @class
 * @prop {number} height
 * @prop {number} width
 * @prop {number} borderRadius
 * @prop {number} x
 * @prop {number} y
 * @prop {string} color
 */
exports.Paddle = function Paddle () {
  this.height = 30
  this.width = 120
  this.borderRadius = 4

  this.x = (constants.C_WIDTH - this.width) / 2
  this.y = constants.C_HEIGHT - this.height - 10

  this.color = 'white'

  /**
   * Move the paddle horizontally
   * @param {number} dx - Relative change in x
   */
  this.move = function (dx) {
    this.x += dx
  }

  /**
   * Daw the paddle on the screen
   */
  this.draw = function () {
    fill(this.color)
    rect(this.x, this.y, this.width, this.height, this.borderRadius)
  }
}
