/**
 * @module views/game_started
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_started_view')
}

exports.show = function show () {
  showView(els.container)
}
