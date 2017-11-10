
/**
 * @module views/created_game
 */

const { showView } = require('../utils')
const QRious = require('qrious')

const els = exports.els = {
  container: $('#created_game_success_view'),
  createdGameKey: $('#created_game_key')
}

/**
 * Show the created game view
 * @param {string} key - Game session key
 */
exports.show = function show (key) {
  els.createdGameKey.text(key)

  const qr = new QRious({
    element: document.getElementById('qr_code_canvas'),
    size: 200,
    value: key
  })

  showView(els.container)
}
