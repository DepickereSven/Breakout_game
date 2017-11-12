/**
 * @module bodies/ball
 */

/**
 * Represents the ball
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} y - vertical position
 * @prop {number} h - height
 * @prop {number} w - width
 */
exports.Ball = class Ball {
  constructor () {
    this.h = 0
    this.w = 0

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {object} bodyObj 
   */
  update ({ h, w, x, y }) {
    this.h = h
    this.w = w
    this.x = x
    this.y = y
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(this.color)
    s.rect(this.x, this.y, this.h, this.w, this.w)
  }
}
