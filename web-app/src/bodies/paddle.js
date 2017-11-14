/**
 * @module bodies/paddle
 */

/**
 * Represents paddle
 * @class
 * @prop {number} h - height
 * @prop {number} w - width
 * @prop {number} borderRadius
 * @prop {number} x
 * @prop {number} y
 * @prop {string} color
 */
exports.Paddle = class Paddle {
  constructor () {
    this.h = 14
    this.w = 0
    this.borderRadius = 4

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {number[]} bodyObj 
   */
  update ([ x, y, w ]) {
    this.w = w
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
    s.rect(this.x, this.y, this.w, this.h, this.borderRadius)
  }
}
