/**
 * @module socket/client
 */

const constants = require('../constants')
const { requestActionsMap } = require('../actions/index')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
 * @prop {String} clientId - UUID that the socket server gives to our client with the ConnectionSuccessAction
 */
class WsClient {
  /**
   * Open connection
   * @method
   */
  open () {
    if (this.ws !== undefined && this.ws.readyState !== WebSocket.CLOSED) {
      throw new Error('WebSocket is already opened.')
    }

    this.clientId = null

    this.ws = new WebSocket(constants.API_URL)

    this.ws.onopen = this.onOpen
    this.ws.onclose = this.onClose
    this.ws.onmessage = this.onMessage
  }

  /**
   * Set the clientId
   * Only done once
   * @method
   */
  setClientId (clientId) {
    this.clientId = clientId
  }

  /**
   * Event handler for succesfull connection
   * @method
   */
  onOpen () {
  }

  /**
   * Event handler for connection loss
   * @method
   */
  onClose () {
    throw new Error('WebSocket was closed.')
  }

  /**
   * Event handler for receicing messages
   * @method
   */
  onMessage (event) {
    const action = JSON.parse(event.data)
    console.log(action)
    const RequestAction = requestActionsMap[action.type]
    if (RequestAction) {
      const a = new RequestAction(action)
      a.handler()
    }
  }

  /**
   * Send an action to the server
   * @method
   * @param {RequestAction} action
   */
  send (action) {
    if (!this.ws) {
      throw new Error("Websocket isn't yet open")
    }

    // Set action type as the name of the class
    action.type = action.constructor.name

    const json = JSON.stringify(action)
    this.ws.send(json)
  }
}

exports.wsClient = new WsClient()
