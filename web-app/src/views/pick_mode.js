const path = 'modes.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
  }
  onLoad () {
  }

  onUnload () {
  }
}
