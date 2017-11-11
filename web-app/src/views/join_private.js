const constants = require('../constants')
const { JoinPrivateGameRequestAction } = require('../actions/join_private_game_request')

const path = 'join_private.html'
exports.path = path

exports.view = class JoinPrivate {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.submitButton = '#submit_and_join_private_game'
    this.scanQrCodeButton = '#start_QR_scan'
    this.codeInput = '#code_for_join_private_game'
    
    this.handleSubmitButtonClick = this.handleSubmitButtonClick.bind(this)
  }

  handleKeyRetrieval (key) {
    if (key.length !== 5) {
      return
    }
    window.wsClient.send(new JoinPrivateGameRequestAction(key))
  }

  handleSubmitButtonClick () {
    const key = $(this.codeInput)
      .val()
      .toUpperCase()
    this.handleKeyRetrieval(key)
  }

  onLoad () {
    $(this.submitButton).on('click', this.handleSubmitButtonClick.bind(this))
    if (constants.IS_ANDROID_APP) {
      window.fill = this.handleSubmitButtonClick
      $(this.scanQrCodeButton).on('click', SmashIt.startQRCode)
    }
  }

  onUnload () {
    $(this.submitButton).off('click')
    if (constants.IS_ANDROID_APP) {
      window.fill = undefined
      $(this.scanQrCodeButton).off('click')
    }
  }
}
