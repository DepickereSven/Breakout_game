const path = 'meet.html'
exports.path = path

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.viewManager = viewManager
    this.remove = true
  }
  onLoad () {
  }

  onUnload () {

  }
}
