/**
 * @module views/game_loss
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_loss_view')
}

exports.show = function show () {
  showView(els.container)
}
