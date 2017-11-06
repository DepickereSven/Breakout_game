/**
 * @module actions/connection_success.js
 */

exports.ConnectionSuccessAction = class ConnectionSuccessAction {
  constructor ({ clientId }) {
    this.clientId = clientId
  }

  handler () {
    window.wsClient.setClientId(this.clientId)
  }
}
