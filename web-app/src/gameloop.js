/**
 * @module gameLoop
 */

const { Player } = require('./player')
const { Ball } = require('./bodies/ball')
const { sketch } = require('./sketch')
const { MovePaddleLeftAction } = require('./actions/move_paddle_left')
const { MovePaddleRightAction } = require('./actions/move_paddle_right')

/**
 * @param {string} str 
 */
const firstLetterToLowerCase = str => str[0].toLowerCase() + str.slice(1)

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle[]} paddles
 * @prop {Ball} ball
 */
class GameLoop {
  constructor () {
    this.reset()
  }

  reset () {
    // Initialise bodies
    this.players = [new Player(true), new Player()]
    this.ball = new Ball()
  }

  updatePlayers (players) {
    for (let i = 0; i < this.players.length; i++) {
      this.players[i].update(players[i])
    }
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object[]} bodyObj 
   */
  updateBodies (bodies) {
    for (const bodyObj of bodies) {
      const instanceKey = firstLetterToLowerCase(bodyObj.type)
      this[instanceKey].update(bodyObj)
    }

    // keyIsPressed left and right arrows does not work in firefox
    // so we need to use keyIsDown
    if (sketch.keyIsDown(sketch.LEFT_ARROW)) {
      window.wsClient.send(new MovePaddleLeftAction())
    }

    if (sketch.keyIsDown(sketch.RIGHT_ARROW)) {
      window.wsClient.send(new MovePaddleRightAction())
    }

    this.run()
  }

  /**
   * Draws the current state onto the provided sketch
   * @method
   */
  run () {
    // Clear canvas
    sketch.background(0)

    for (const player of this.players) {
      player.paddle.draw(sketch)
      player.score.draw(sketch)
    }

    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()
