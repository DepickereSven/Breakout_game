/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const utils = require('./utils')

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')
const { BrickRow } = require('./bodies/brick')
const { Score } = require('./bodies/score')

const state = {}

function addBrickRow () {
  // Move other rows down
  for (const row of state.brickRows) {
    row.moveDown()
  }

  // Create new row
  const rowIndex = state.brickRows.length
  const row = new BrickRow(rowIndex)

  // Place in on top
  state.brickRows.unshift(row)
}

function removeBrickFromRow (brickRow, brick) {
  brickRow.removeBrick(brick)

  // Check if bottom row is empty
  const lastIndex = state.brickRows.length - 1
  const isBottomRow = brickRow == state.brickRows[lastIndex]

  if (isBottomRow && brickRow.isEmpty()) {
    state.brickRows.pop()
  }
}

const p5 = new P5(function (sketch) {

  // Set globals
  window.fill = sketch.fill.bind(sketch)
  window.background = sketch.background.bind(sketch)
  window.rect = sketch.rect.bind(sketch)
  window.ellipse = sketch.ellipse.bind(sketch)
  window.textFont = sketch.textFont.bind(sketch)
  window.text = sketch.text.bind(sketch)

  sketch.setup = function () {
    sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)

    // Initialise paddle
    state.paddle = new Paddle()
    state.paddleCollisions = 0

    // Initialise ball
    state.ball = new Ball()

    // Initialise bricks
    state.brickRows = []
    addBrickRow()
    addBrickRow()

    // Initialise score
    state.score = new Score()
  }

  sketch.draw = function () {
    // Clear canvas
    background(0)
    fill(255)

    // Paddle movement
    if (state.paddle) {
      const { x, y, width } = state.paddle

      if (sketch.keyIsPressed && sketch.keyCode == sketch.LEFT_ARROW && x > 0) {
        state.paddle.move(-8)
      }
      if (sketch.keyIsPressed && sketch.keyCode == sketch.RIGHT_ARROW && x < constants.C_WIDTH - width) {
        state.paddle.move(8)
      }

      state.paddle.draw()
    }

    // Ball movement
    if (state.ball) {
      let { x, y, dx, dy, radius } = state.ball

      // Wall collission
      if (x + dx > constants.C_WIDTH - radius || x + dx < radius) {
        dx = -dx

        // Ceiling collission
      } else if (y + dy < radius) {
        dy = -dy

        // Floor collission
      } else if (y + dy > constants.C_HEIGHT - radius) {
        console.log('GAME OVER')
        setup()
        return

        // Paddle collision
      } else if (utils.isBallCollision(state.ball, state.paddle)) {
        dy = -dy
        state.paddleCollisions += 1
        if (state.paddleCollisions > 4) {
          addBrickRow()
          state.paddleCollisions = 0
        }

        // Brick collision
      } else {
        // Reverse loop over the brickRows -> bottom rows will be first checked
        for (let i = state.brickRows.length - 1; i >= 0; i--) {
          const brickRow = state.brickRows[i]
          const brick = brickRow.isBallCollision(state.ball)

          if (brick) {
            removeBrickFromRow(brickRow, brick)

            state.score.add()
            dy = -dy
            break
          }
        }
      }

      state.ball.move(dx, dy)
      state.ball.draw()
    }

    for (const brickRow of state.brickRows) {
      brickRow.draw()
    }

    state.score.draw()
  }
})


