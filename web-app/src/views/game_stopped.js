/**
 * @module views/game_stopped
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_stopped')
}

exports.show = function show () {
  showView(els.container)
}
