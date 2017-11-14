/**
 * @module bodies/score
 */

const constants = require('../constants')

/**
 * Represents the user score
 * @class
 * @prop {number} score
 * @prop {string} color
 */
exports.Score = class Score {
  constructor (currentPlayer = false) {
    const gap = constants.C_HEIGHT * 0.20
    this.y = currentPlayer ? constants.C_HEIGHT - gap : gap
    this.points = 0
    this.color = 'white'
  }

  /**
   * @method
   */
  update (p) {
    this.points = p
  }

  /**
   * Draws the score on the screen
   * @method
   */
  draw (s) {
    s.fill(this.color)
    s.textFont('Arial', 20)
    s.textAlign(s.CENTER)
    s.text(this.points, constants.C_WIDTH / 2, this.y)
  }
}
