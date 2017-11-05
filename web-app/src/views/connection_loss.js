/**
 * @module views/connection_loss
 */

const { showView } = require('../utils')

const els = {
  container: $('#connection_loss_view')
}

exports.show = function show () {
  showView(els.container)
}
