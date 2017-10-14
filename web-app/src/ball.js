const constants = require("./constants")
const utils = require("./utils")

/**
 * Represents the ball
 * @constructor
 * @prop {int} x - horizontal position
 * @prop {int} y - vertical position
 * @prop {int} dx - horizontal speed
 * @prop {int} dy - vertical speed
 */
function Ball () {
  this.x = constants.C_WIDTH / 2
  this.y = constants.C_HEIGHT - 80

  this.dx = utils.randomInRange(4, 6) * utils.randomSign()
  this.dy = -8

  this.radius = 10
  this.color = 'white'

  /**
   * Move the ball to new position
   * @method
   * @param {int} dx
   * @param {int} dy
   */
  this.move = function (dx, dy) {
    this.dx = dx
    this.dy = dy

    this.x += dx
    this.y += dy
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   */
  this.draw = function () {
    fill(this.color)
    ellipse(this.x, this.y, this.radius * 2)
  }
}

module.exports = { Ball }
