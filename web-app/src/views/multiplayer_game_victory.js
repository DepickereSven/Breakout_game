const path = 'multiplayer_game_won.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.remove = true

    this.viewManager = viewManager
    this.home = '#home'
    this.shop = '#shop_multiplayer'

    this.goHome = this.goHome.bind(this)
    this.goToShop = this.goToShop.bind(this)
  }

  goToShop (e) {
    this.viewManager.go('multiplayer_menu.html')
  }
  goHome () {
    this.viewManager.goHome()
  }

  onLoad () {
    $(this.home).on('click', this.goHome)
    $(this.shop).on('click', this.goToShop)
  }

  onUnload () {
    $(this.home).off('click')
    $(this.shop).off('click')
  }
}
