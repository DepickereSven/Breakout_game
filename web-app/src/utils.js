/**
 * @module utils
 */

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

function timePadding (time, isBack) {
  const newtime = time.toString()
  if (newtime.length < 2) {
    return isBack ? newtime + '0' : '0' + newtime
  } else {
    return newtime
  }
}

function displayTime (timeInSec) {
  const min = Math.floor(timeInSec / 60)
  const sec = timeInSec - (min * 60)
  return timePadding(min) + ':' + timePadding(sec)
}
exports.displayTime = displayTime
