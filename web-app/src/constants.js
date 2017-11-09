/**
 * @module constants
 */

/**
 * API url
 * @type {string}
 */
exports.API_URL = ((window.location.protocol === 'https:') ? 'wss://' : 'ws://') + window.location.host + '/breakout/socket'

/**
 * Canvas height
 * @type {number}
 */
exports.C_HEIGHT = 480

/**
 * Canvas width
 * @type {number}
 */
exports.C_WIDTH = 320
