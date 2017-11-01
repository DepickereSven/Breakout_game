/**
 * @module socket/client
 */

const msgpack = require('msgpack-lite')

const constants = require('../constants')
const connectionLossView = require('../views/connection_loss')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
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

    this.ws = new WebSocket(constants.API_URL)
    this.ws.binaryType = 'arraybuffer'

    this.ws.onopen = this.onOpen
    this.ws.onclose = this.onClose
    this.ws.onmessage = this.onMessage
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
    connectionLossView.show()
    throw new Error('WebSocket was closed.')
  }

  /**
   * Event handler for receicing messages
   * @method
   */
  onMessage (event) {
    const bufferView = new Uint8Array(event.data)
    const action = msgpack.decode(bufferView)
    console.log(action)
  }

  /**
   * Send an action to the server
   * @method
   * @param {RequestAction} action
   */
  send (action) {
    if (!this.ws) {
      throw new Error('Websocket isn\'t yet open')
    }

    // Set action type as the name of the class
    action.type = action.constructor.name

    const buffer = msgpack.encode(action)
    console.log(msgpack.decode(buffer))
    this.ws.send(buffer)
  }
}

exports.wsClient = new WsClient()
