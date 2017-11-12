const path = 'multiplayer_game_won.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.home = '#home'
    this.shop = '#shop_multiplayer'
  }

  sendToShop (e) {
    this.viewManager.go('multiplayer_menu.html')
  }
  goHome () {
    this.viewManager.goHome()
  }
  onLoad () {
    $(this.shop).on('click', this.sendToShop.bind(this))
    $(this.home).on('click', this.goHome.bind(this))
  }

  onUnload () {
  }
}
