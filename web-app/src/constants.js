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

exports.IS_TOUCH_SCREEN = function () {
  return (('ontouchstart' in window) || (navigator.MaxTouchPoints > 0) || (navigator.msMaxTouchPoints > 0))
}

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
 * Array of quotes about winning
 * @type {object[]}
 */
exports.WIN_QUOTES = [
  {text: 'First they ignore you, then they laugh at you, then they fight you, then you win.', author: 'Mahatma Gandh'},
  {text: 'Winners never quit and quitters never win.', author: 'Vince Lombardi'},
  {text: 'You were born to win, but to be a winner, you must plan to win, prepare to win, and expect to win.', author: 'Zig Ziglar'},
  {text: 'You cant win unless you learn how to lose.', author: 'Kareem Abdul-Jabbar'},
  {text: 'You will never win if you never begin.', author: 'Helen Rowland'},
  {text: 'Winning is great, sure, but if you are really going to do something in life, the secret is learning how to lose. Nobody goes undefeated all the time. If you can pick up after a crushing defeat, and go on to win again, you are going to be a champion someday.', author: 'Wilma Rudolph'},
  {text: 'What does it take to be a champion? Desire, dedication, determination, concentration and the will to win.', author: 'Patty Berg'},
  {text: 'Win or lose, I ll feel good about myself. Thats what is important.', author: 'Mary Docter'},
  {text: 'Victorious warriors win first and then go to war, while defeated warriors go to war first and then seek to win.', author: 'Sun Tzu'},
  {text: 'You may have to fight a battle more than once to win it.', author: 'Margaret Thatcher'},
  {text: 'No matter if you win or lose, the most important thing in life is to enjoy what you have.', author: 'Dong Dong'},
  {text: 'Haters never win. I just think thats true about life, because negative energy always costs in the end.', author: 'Tom Hiddleston'},
  {text: 'I race to win. If I am on the bike or in a car it will always be the same.', author: 'Valentino Rossi'},
  {text: 'I am really exciting. I smile a lot, I win a lot, and I am really sexy.', author: 'Serena Williams'},
  {text: 'To be a good loser is to learn how to win.', author: 'Carl Sandburg'},
  {text: 'The only thing I can do is fight. Win or lose, I am here every damn time.', author: 'Donald Cerrone'},
  {text: 'Win or lose today I am proud of the way my boys have played in the tournament.', author: 'Imran Khan'},
  {text: 'I dont care what it is in life: listen to your heart. If you do, no matter what, you win.', author: 'Paul Walker'}
]
/**
 * The length of the screen transition animation
 * @type {number}
 */
exports.SCREEN_ANIMATION_TIME = 300

/**
 * Google application client id
 * @type {string}
 */
exports.G_CLIENT_ID = '870997935508-c4325ugimh126ub88kl8o5c8nr2ms6ot.apps.googleusercontent.com'
