/**
 * @module bodies/paddle
 */

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
exports.Paddle = class Paddle {
  constructor () {
    this.height = 0
    this.width = 0
    this.borderRadius = 4

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {object} bodyObj 
   */
  update ({ height, width, x, y }) {
    this.height = height
    this.width = width
    this.x = x
    this.y = y
  }

  /**
   * Daw the paddle on the screen
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(this.color)
    s.rect(this.x, this.y, this.width, this.height, this.borderRadius)
  }
}
