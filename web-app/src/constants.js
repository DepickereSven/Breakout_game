/**
 * @module constants
 */

/**
 * API url
 * @type {string}
 */
exports.API_URL = 'ws://localhost:8080/breakout/socket'

/**
 * Canvas height
 * @type {number}
 */
exports.C_HEIGHT = 450

/**
 * Canvas width
 * @type {number}
 */
exports.C_WIDTH = 300

/**
 * Is the current client the webview in the android app
 * @type {string}
 */
exports.IS_ANDROID_APP = navigator.userAgent.indexOf('Smash_It') > 1
