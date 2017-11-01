
/**
 * @module views/created_game
 */

const { showView } = require('../utils')

const els = exports.els = {
  container: $('#created_game_success'),
  createdGameKey: $('#created_game_key')
}

/**
 * Show the created game view
 * @param {string} key - Game session key
 */
exports.show = function show (key) {
  els.createdGameKey.text(key)
  showView(els.container)
}
