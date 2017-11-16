const path = 'loading.html'
exports.path = path

exports.view = class LoadingView {
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
