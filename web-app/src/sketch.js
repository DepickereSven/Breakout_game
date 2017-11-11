/**
 * @module sketch
 */

const P5 = require('p5')
const constants = require('./constants')

exports.createSketch = function createSketch () {
  return new P5(function (sketch) {
    sketch.setup = function () {
      const canvas = sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)
      canvas.parent('game_container')

      // Don't loop on its own because we draw manualy when the server send and update
      sketch.noLoop()
    }
    sketch.draw = () => {}
  })
}
