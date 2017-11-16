/**
 * @module actions/user_login_success.js
 */

const { viewManager } = require('../views/index')

exports.handler = function () {
  viewManager.goHome()
}
