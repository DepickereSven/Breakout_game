/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const { gameLoop } = require('./gameloop')
const { wsClient } = require('./socket/client')
const initGameView = require('./views/init_game')

$(document).ready(function () {
  initGameView.show()
  wsClient.open()
})

const p5 = new P5(function (sketch) {
  sketch.setup = function () {
    const canvas = sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)
    canvas.parent('game_started')
  }

  sketch.draw = () => gameLoop.run(sketch)
})
