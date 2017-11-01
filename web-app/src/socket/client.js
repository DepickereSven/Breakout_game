/**
 * @module socket/client
 */

const msgpack = require('msgpack-lite');

const constants = require('../constants')
const initGameView = require('../views/init_game')
const connectionLossView = require('../views/connection_loss')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
 */
function WsClient(){
}


/**
 * Open connection
 * @method
 */
WsClient.prototype.open = function open() {
  if(this.ws !== undefined && this.ws.readyState !== WebSocket.CLOSED){
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
WsClient.prototype.onOpen = function onOpen() {
  initGameView.show()
}

/**
 * Event handler for connection loss
 * @method
 */
WsClient.prototype.onClose = function onClose() {
  connectionLossView.show()
  throw new Error('WebSocket was closed.')
}

/**
 * Event handler for receicing messages
 * @method
 */
WsClient.prototype.onMessage = function onMessage(event) {
  const bufferView = new Uint8Array(event.data)
  const action = msgpack.decode(bufferView)
  console.log(action)
}

/**
 * Send an action to the server
 * @method
 * @param {Action} action
 */
WsClient.prototype.send = function send(action) {
  if(!this.ws){
    throw new Error('Websocket isn\'t yet open')
  }
  console.log(action)
  const buffer = msgpack.encode(action)
  this.ws.send(buffer)
}


// Export a single WsClient instance
if(!window.wsClient){
  window.wsClient = new WsClient()
}

exports.wsClient = window.wsClient
