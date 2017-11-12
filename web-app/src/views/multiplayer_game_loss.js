const path = 'multiplayer_game_lost.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.viewManager = viewManager
    this.menu = '#menu_multiplayer'
    this.shop = '#shop_multiplayer'
  }

  sendToShop (e) {
    const data = $(e.currentTarget).data()
    this.viewManager.go('multiplayer_menu.html', data)
  }
  sendBackToMenu (e) {
    const data = $(e.currentTarget).data()
    this.viewManager.go('multiplayer_menu.html', data)
  }
  onLoad () {
    $(this.shop).on('click', this.sendToShop.bind(this))
    $(this.menu).on('click', this.sendBackToMenu.bind(this))
  }

  onUnload () {
  }
}
