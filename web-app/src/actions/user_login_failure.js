/**
 * @module actions/user_login_failure.js
 */

const { viewManager } = require('../views/index')
const { setToken } = require('../user')

exports.handler = function () {
  setToken(null)
  viewManager.go('login.html')
}
