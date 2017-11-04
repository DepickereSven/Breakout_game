/**
 * @module views/loading
 */

const { showView } = require('../utils')

const els = {
  container: $('#loading')
}

exports.show = function show () {
  showView(els.container)
}