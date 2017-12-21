/**
 * @module actions/user_login_success.js
 */

const { User } = require('../user')
const { viewManager } = require('../views/index')

exports.handler = function ({ user }) {
  window.user = new User(user)
  viewManager.goHome()
}
