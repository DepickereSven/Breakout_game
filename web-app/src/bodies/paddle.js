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
function Paddle () {
  this.height = 0
  this.width = 0
  this.borderRadius = 4

  this.x = 0
  this.y = 0

  this.color = 'white'
}
exports.Paddle = Paddle


/**
 * Daw the paddle on the screen
 * @method
 * @param {Sketch} s
 */
Paddle.prototype.draw = function (s) {
  s.fill(this.color)
  s.rect(this.x, this.y, this.width, this.height, this.borderRadius)
}

