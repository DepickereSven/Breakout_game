const path = 'privacy_policy.html'
exports.path = path

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.allowUnAuth = true
    this.viewManager = viewManager
  }
  onLoad () {
    $('.coins').hide()
  }

  onUnload () {

  }
}
