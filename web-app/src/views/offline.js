const path = 'offline.html'
exports.path = path

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.allowUnAuth = true
    this.remove = true
  }
  onLoad () {
  }

  onUnload () {
  }
}
