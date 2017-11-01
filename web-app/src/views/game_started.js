/**
 * @module views/game_started
 */

const { showView } = require('../utils')

const els = exports.els = {
  container: $('#created_game_success_modal')
}


exports.show = function show() {
  showView(els.container)
}
