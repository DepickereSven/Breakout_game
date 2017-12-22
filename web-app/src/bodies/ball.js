/**
 * @module bodies/ball
 */
const constants = require('../constants')

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

    this.color = [255, 255, 255, 200]
    this.trailColor = [223, 116, 12]
    this.history = []
    this.historyLength = 7
  }

  /**
   * Update body to match the server state
   * @param {number[]} bodyObj 
   */
  update ([ x, y, size ]) {
    this.x = x
    this.y = y
    this.size = size

    this.history.push({ x, y })
    if (this.history.length > this.historyLength) {
      this.history.shift()
    }
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.noStroke()
    for (let i = 0; i < this.history.length; i++) {
      let { x, y } = this.history[i]
      const alpha = 35 + i * 20

      const mult = (this.history.length - i) * 0.5
      const size = this.size - mult
      x += mult

      const [ r, g, b ] = this.trailColor
      s.fill(r, g, b, alpha)
      s.rect(x, y, size, size, size)
    }
    s.fill(this.color)
    s.stroke(this.trailColor)
    s.rect(this.x, this.y, this.size, this.size, this.size)
    s.stroke('black')
  }
}
