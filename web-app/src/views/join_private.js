const constants = require('../constants')
const JoinPrivateGameRequestAction = require('../actions/join_private_game_request')

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
    this.handleKeyRetrieval = this.handleKeyRetrieval.bind(this)
  }

  handleKeyRetrieval (key) {
    if (key.length !== 5) {
      return
    }
    window.wsClient.send(JoinPrivateGameRequestAction.create(key))
  }

  handleSubmitButtonClick () {
    const key = $(this.codeInput)
      .val()
      .toUpperCase()
    this.handleKeyRetrieval(key)
  }

  handleScanQrButtonClick () {
    SmashIt.startQRCode()
  }

  onLoad () {
    window.onAndroidQrScan = this.handleKeyRetrieval

    $(this.submitButton).on('click', this.handleSubmitButtonClick.bind(this))
    if (constants.IS_ANDROID_APP) {
      window.fill = this.handleSubmitButtonClick
      $(this.scanQrCodeButton).on('click', this.handleScanQrButtonClick)
    }
  }

  onUnload () {
    window.onAndroidQrScan = undefined

    $(this.submitButton).off('click')
    if (constants.IS_ANDROID_APP) {
      window.fill = undefined
      $(this.scanQrCodeButton).off('click')
    }
  }
}
