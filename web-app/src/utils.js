/**
 * @module utils
 */

/**
 * Calculate distance between 2 points
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 * @return {number}
 */
function calcPointsDistance (x1, y1, x2, y2) {
  let xDist = x2 - x1
  let yDist = y2 - y1

  // Pythagoras
  return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2))
}
exports.calcPointsDistance = calcPointsDistance

/**
 * Check if value is between min and max
 * @param {number} val
 * @param {number} min
 * @param {number} max
 * @return {boolean}
 */
function inRange (val, min, max) {
  return val >= Math.min(min, max) && val <= Math.max(min, max)
}
exports.inRange = inRange

/**
 * Check if there is a collision between ball and brick
 * @param {Ball} ball
 * @param {Brick} brick
 * @return {boolean}
 */
function isBallCollision (ball, brick) {
  return inRange(ball.x, brick.x - ball.radius, brick.x + brick.width + ball.radius) &&
    inRange(ball.y, brick.y - ball.radius, brick.y + brick.height + ball.radius)
}
exports.isBallCollision = isBallCollision

/**
 * Generate random number between min and max
 * @param {number} min
 * @param {number} max
 * @return {number}
 */
function randomInRange (min, max) {
  min = Math.ceil(min)
  max = Math.floor(max)
  return Math.round(Math.random() * (max - min)) + min
}
exports.randomInRange = randomInRange

/**
 * Generate either 1 or -1
 * @return {number}
 */
function randomSign () {
  return Math.round(Math.random()) ? 1 : -1
}
exports.randomSign = randomSign

/**
 * Generate random color in RGB
 * @return {number[]}
 */
function randomColor () {
  return [0, 0, 0].map(() => randomInRange(50, 255))
}
exports.randomColor = randomColor

/**
 * Show the given view and hide the others
 * @param {jQuery} el - jQuery container element
 */
function showView (el) {
  $('.view').addClass('hidden')
  el.removeClass('hidden')
}
exports.showView = showView
