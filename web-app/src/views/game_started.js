/**
 * @module views/game_started
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_started')
}

exports.show = function show () {
  showView(els.container)
}
