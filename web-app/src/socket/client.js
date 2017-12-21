/**
 * @module socket/client
 */

const constants = require('../constants')
const { responseActionsMap } = require('../actions/index')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
 * @prop {String} clientId - UUID that the socket server gives to our client with the ConnectionSuccessAction
 */
class WsClient {
  constructor () {
    this.open = this.open.bind(this)
    this.reset = this.reset.bind(this)
    this.setClientId = this.setClientId.bind(this)
    this.onOpen = this.onOpen.bind(this)
    this.onClose = this.onClose.bind(this)
    this.onMessage = this.onMessage.bind(this)
    this.send = this.send.bind(this)
  }

  /**
   * Open connection
   * @method
   * @param {function} callback
   */
  open (callback) {
    if (this.ws !== undefined && this.ws.readyState === WebSocket.OPEN) {
      throw new Error('WebSocket is already opened.')
    }
    this.callback = callback
    this.reset()
    window.addEventListener('offline', () => this.ws.close())
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
   * Event handler for connection opening
   * @method
   */
  onOpen () {
    this.callback()
  }

  /**
   * Event handler for connection loss
   * @method
   */
  onClose () {
    const view = window.viewManager.getCurrentView()
    if (!view || view.path !== 'offline.html') {
      window.viewManager.go('offline.html')
    }
    console.error(new Error('WebSocket was closed.'))
    // Retry connection
    setTimeout(this.reset, 2000)
  }

  /**
   * Event handler for receicing messages
   * @method
   */
  onMessage (event) {
    const action = JSON.parse('{' + event.data + '}')
    const requestAction = typeof action.t === 'string'
      ? responseActionsMap[action.t]
      : responseActionsMap.GameStateUpdateAction
    if (requestAction) {
      requestAction.handler(action)
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

    const json = JSON.stringify(action)
    this.ws.send(json)
  }

  /**
   * Reset websocket connection
   * @method
   */
  reset () {
    if (this.ws) {
      this.ws.close()
    }
    this.clientId = null

    this.ws = new WebSocket(constants.API_URL)

    this.ws.onopen = this.onOpen
    this.ws.onclose = this.onClose
    this.ws.onmessage = this.onMessage
  }
}

exports.wsClient = new WsClient()
