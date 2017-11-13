/**
 * @module actions/create_game_success.js
 */

const { viewManager } = require('../views/index')

exports.handler = function ({ key }) {
  viewManager.go('create_private_success.html', { key })
}
