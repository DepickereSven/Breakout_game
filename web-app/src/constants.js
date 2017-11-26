/**
 * @module constants
 */

/**
 * API url
 * @type {string}
 */
// exports.API_URL = (window.location.origin + window.location.pathname).replace(/^http/, 'ws').replace(/\/$/, '') + '/socket'
exports.API_URL = 'ws://smash-it.nu/socket'

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

/**
 * Is the current client the webview in the android app
 * @type {string}
 */
exports.IS_ANDROID_APP = navigator.userAgent.indexOf('Smash_It') > 1

/**
 * Array of quotes about losing
 * @type {object[]}
 */
exports.LOSS_QUOTES = [
  {text: 'Sometimes by losing a battle you find a new way to win the war.', author: 'Donald Trump'},
  {text: 'The only person you\'re truly competing against is yourself.', author: 'Jean-Luc Picard'},
  {text: 'First, accept sadness. Realize that without losing, winning isn\'t so great.', author: 'Alyssa Milano'},
  {text: 'You learn more from losing than winning. You learn how to keep going.', author: 'Morgan Wootten'},
  {text: 'Winning and losing isn\'t everything; sometimes, the journey is just as important as the outcome.', author: 'Alex Morgan'},
  {text: 'If you\'ve had a good time playing the game, you\'re a winner even if you lose.', author: 'Malcolm Forbes'},
  {text: 'The biggest thing I learned from losing? Winning\'s better.', author: 'Ted Turner'},
  {text: 'Every defeat, every heartbreak, every loss, contains its own seed, its own lesson on how to improve your performance the next time.', author: 'Malcolm X'},
  {text: 'Everything is a learning process: any time you fall over, it\'s just teaching you to stand up the next time.', author: 'Joel Edgerton'}
]

/**
 * The length of the screen transition animation
 * @type {number}
 */
exports.SCREEN_ANIMATION_TIME = 300
