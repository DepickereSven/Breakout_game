const { WIN_QUOTES } = require('../constants.js')
const utils = require('../utils')

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
    this.quoteText = '#quote-text'
    this.quoteAuthor = '#quote-author'

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

    const quote = WIN_QUOTES[utils.randomInRange(0, WIN_QUOTES.length - 1)]
    $(this.quoteText).text(quote.text)
    $(this.quoteAuthor).text('- ' + quote.author + ' -')
  }

  onUnload () {
    $(this.home).off('click')
    $(this.shop).off('click')
  }
}
