/**
 * @module actions/user_login_failure.js
 */

const { viewManager } = require('../views/index')

exports.handler = function () {
  viewManager.go('login.html')
}
