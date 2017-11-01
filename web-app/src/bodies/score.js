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
exports.Score = class Score {
  constructor(){
    this.score = 0
    this.color = 'white'
  }

  /**
   * Increases the score by 1
   * @method
   */
  add() {
    this.score += 1
  }

  /**
   * Get the current score
   * @method
   * @return {number}
   */
  get() {
    return this.score
  }

  /**
   * Draws the score on the screen
   * @method
   */
  draw() {
    fill(color)
    textFont('Arial', 30)
    text(score, constants.C_WIDTH / 2, constants.C_HEIGHT / 2)
  }
}
