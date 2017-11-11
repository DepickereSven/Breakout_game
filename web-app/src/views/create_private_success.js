const QRious = require('qrious')

const path = 'create_private_success.html'
exports.path = path

exports.view = class CreatePrivateSuccessView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true

    this.gameCode = '#code'
  }

  onLoad ({ key }) {
    $(this.gameCode).text(key)

    const qr = new QRious({
      element: document.getElementById('qr_code_canvas'),
      size: 200,
      value: key
    })
  }

  onUnload () {
  }
}
