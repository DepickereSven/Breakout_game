/**
 * @module initialize
 */

const P5 = require('p5')

const { wsClient } = require('./socket/client')
const initGameView = require('./views/init_game')

require('./sketch')

$(document).ready(function () {
  initGameView.show()
  wsClient.open()
})
