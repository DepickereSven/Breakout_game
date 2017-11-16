/**
 * @module actions/user_login_request.js
 */

exports.create = (user) => Object.assign({ t: 'UserLoginRequestAction' }, user)
