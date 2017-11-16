const QRious = require('qrious')
const CancelMultiplayerRequestAction = require('../actions/cancel_multiplayer_request')

const path = 'create_private_success.html'
exports.path = path

exports.view = class CreatePrivateSuccessView {
  constructor (viewManager, { key }) {
    this.path = path
    this.viewManager = viewManager
    this.key = key

    this.gameCode = '#code'
    this.shareButton = '#sharingIsCaring'

    this.handleBack = this.handleBack.bind(this)
  }

  handleBack () {
    window.wsClient.send(CancelMultiplayerRequestAction.create())
  }

  handleScanQrButtonClick () {
    const code = $('#code').html()
    SmashIt.sharingIsCaring(code)
  }

  onLoad () {
    window.addEventListener('back', this.handleBack)
    $(this.gameCode).text(this.key)
    $(this.shareButton).on('click', this.handleScanQrButtonClick)
    const qr = new QRious({
      element: document.getElementById('qr_code_canvas'),
      size: 200,
      value: this.key
    })
  }

  onUnload () {
    window.removeEventListener('back', this.handleBack)
  }
}
