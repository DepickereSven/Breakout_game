/**
 * @module views/loading
 */

const { showView } = require('../utils')

const els = {
  container: $('#loading_view')
}

exports.show = function show () {
  showView(els.container)
}
