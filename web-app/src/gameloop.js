/**
 * @module gameLoop
 */

const { Player } = require('./player')
const { Ball } = require('./bodies/ball')
const { sketch } = require('./sketch')

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
    this.players = {}
    this.ball = new Ball()
  }

  updatePlayers (players) {
    for (const player of players) {
      if (!this.players[player.clientId]) {
        const isCurrentPlayer = player.clientId === window.wsClient.clientId
        const isMultiplayer = players.length === 2

        this.players[player.clientId] = new Player(
          isCurrentPlayer,
          isMultiplayer
        )
      }
      this.players[player.clientId].update(player)
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

    this.run()
  }

  /**
   * Draws the current state onto the provided sketch
   * @method
   */
  run () {
    // Clear canvas
    sketch.background(0)

    for (const clientId in this.players) {
      if (!this.players.hasOwnProperty(clientId)) {
        return
      }
      const player = this.players[clientId]
      player.paddle.draw(sketch)
      if (player.score) {
        player.score.draw(sketch)
      }
    }

    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()
