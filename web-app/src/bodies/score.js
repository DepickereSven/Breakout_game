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
  constructor () {
    this.points = 0
    this.color = 'white'
  }

  /**
   * @method
   */
  update ({points}) {
    this.points = points
  }

  /**
   * Draws the score on the screen
   * @method
   */
  draw (s) {
    s.fill(this.color)
    s.textFont('Arial', 30)
    s.text(this.points, constants.C_WIDTH / 2, constants.C_HEIGHT / 2)
  }
}
