/**
 * @module actions/connection_success.js
 */

exports.handler = function ({ clientId }) {
  window.wsClient.setClientId(clientId)
}
