const QRious = require('qrious')

const path = 'create_private_success.html'
exports.path = path

exports.view = class CreatePrivateSuccessView {
  constructor (viewManager, { key }) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true
    this.key = key

    this.gameCode = '#code'
    this.shareButton = '#sharingIsCaring'
  }

  handleScanQrButtonClick () {
    const code = $('#code').html()
    SmashIt.sharingIsCaring(code)
  }

  onLoad () {
    $(this.gameCode).text(this.key)
    $(this.shareButton).on('click', this.handleScanQrButtonClick)
    const qr = new QRious({
      element: document.getElementById('qr_code_canvas'),
      size: 200,
      value: this.key
    })
  }

  onUnload () {
  }
}
