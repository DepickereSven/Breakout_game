/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const { GameLoop } = require('./gameloop')
const { wsClient } = require('./socket/client')

require('./userinput')

const state = {}

const p5 = new P5(function (sketch) {
  let gameLoop

  sketch.setup = function () {
    sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)

    wsClient.open()

    gameLoop  = new GameLoop()
  }

  sketch.draw = function () {
    gameLoop.run(sketch)
  }
})


