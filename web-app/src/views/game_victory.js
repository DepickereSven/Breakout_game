/**
 * @module views/game_victory
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_victory_view')
}

exports.show = function show () {
  showView(els.container)
}
