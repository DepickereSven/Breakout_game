/**
 * @module actions/game_loss.js
 */

// const gameLossView = require('../views/game_loss')

const { viewManager } = require('../views/index')

exports.GameLossAction = class GameLossAction {
  handler () {
    // gameLossView.show()
    const mod = JSON.parse(localStorage.getItem('whatMode'))
    if (mod === 'multi') {
      viewManager.go('multiplayer_game_lost.html')
    } else {
      viewManager.go('singleplayer_game_lost.html')
    }
  }
}
