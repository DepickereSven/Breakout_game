/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const { GameLoop } = require('./gameloop')
const { wsClient } = require('./socket/client')
const initGameView = require('./views/init_game')

const p5 = new P5(function (sketch) {
  let gameLoop

  sketch.setup = function () {
    const canvas = sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)
    canvas.parent('game_started')

    initGameView.show()
    wsClient.open()

    gameLoop = new GameLoop()
  }

  sketch.draw = function () {
    gameLoop.run(sketch)
  }
})


