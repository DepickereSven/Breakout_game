/**
 * @module bodies/brick
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents a brick
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} height
 * @prop {number} width
 * @prop {number[]} color
 */
exports.Brick = class Brick {
  constructor(x, height, width) {
    this.x = x
    this.y = 0
    this.height = height
    this.width = width
    this.color = utils.randomColor()
  }

  /**
   * Move the ball to new position
   * @method
   * @param {int} dx
   * @param {int} dy
   */
  move(dx, dy) {
    this.x += dx
    this.y += dy
  }

  /**
   * Draw the brick on the screen
   * @method
   */
  draw() {
    fill.apply(fill, this.color)
    rect(this.x, this.y, this.width, this.height)
  }
}

/**
 * Represents a row of bricks
 * @class
 * @param {number} rowIndex - The index of row
 * @prop {Brick[]} bricks
 */
exports.BrickRow = class BrickRow {
  constructor(rowIndex){
    const count = 8
    const margin = 10
    const height = 30
    const width = (constants.C_WIDTH - count * margin) / count

    // Create new row
    this.bricks = new Array(count)
      .fill(null)
      .map((_, i) => new Brick((width + margin) * i, height, width))
  }

  /**
   * Move the bricks in this row down 1 row
   * @method
   */
  moveDown() {
    for (const brick of this.bricks) {
      brick.move(0, height + margin)
    }
  }

  /**
   * Checks if the ball colides with a brick in the row
   * @method
   * @param {Ball} ball
   * @return {Ball}
   */
  isBallCollision(ball) {
    for (const brick of this.bricks) {
      if (utils.isBallCollision(ball, brick)) {
        return brick
      }
    }
    return null
  }

  /**
   * Removes brick from the row
   * @method
   * @param {Brick} brick
   */
  removeBrick(brick) {
    this.bricks = this.bricks.filter((b) => b !== brick)
  }

  /**
   * Checks if the row is empty
   * @method
   * @return {bool}
   */
  isEmpty() {
    return this.bricks.length < 1
  }

  /**
   * Draws the bricks
   * @method
   */
  draw() {
    for (const brick of this.bricks) {
      brick.draw()
    }
  }
}
