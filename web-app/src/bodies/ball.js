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
    this.x = 0
    this.y = 0
    this.size = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {number[]} bodyObj 
   */
  update ([ x, y, size ]) {
    this.x = x
    this.y = y
    this.size = size
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(this.color)
    s.rect(this.x, this.y, this.size, this.size, this.size)
  }
}
