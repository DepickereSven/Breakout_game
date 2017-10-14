/**
 * @module bodies/score
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents the user score
 * @class
 * @prop {number} score
 * @prop {string} color
 */
exports.Score = function Score () {
  let score = 0
  const color = 'white'

  /**
   * Increases the score by 1
   * @method
   */
  this.add = function () {
    score += 1
  }

  /**
   * Get the current score
   * @method
   * @return {number}
   */
  this.get = function () {
    return score
  }

  /**
   * Draws the score on the screen
   * @method
   */
  this.draw = function () {
    fill(color)
    textFont('Arial', 30)
    text(score, constants.C_WIDTH / 2, constants.C_HEIGHT / 2)
  }
}
