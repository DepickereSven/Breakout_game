const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')
const state = require('../global_state.js')
const { WIN_QUOTES } = require('../constants.js')
const utils = require('../utils')

const path = 'singleplayer_game_won.html'
exports.path = path

exports.view = class SingleplayerGameVictory {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.remove = true

    this.viewManager = viewManager
    this.home = '#home'
    this.next = '#next_singleplayer'
    this.quoteText = '#quote-text'
    this.quoteAuthor = '#quote-author'

    this.goHome = this.goHome.bind(this)
    this.handleNextClick = this.handleNextClick.bind(this)
  }

  handleNextClick () {
    this.viewManager.go('loading.html')
    const level = state.get('currentLevel') + 1
    window.wsClient.send(CreateSingleplayerRequestAction.create(level))
  }
  goHome () {
    this.viewManager.goHome()
  }
  onLoad () {
    $(this.home).on('click', this.goHome)
    $(this.next).on('click', this.handleNextClick)

    const quote = WIN_QUOTES[utils.randomInRange(0, WIN_QUOTES.length - 1)]
    $(this.quoteText).text(quote.text)
    $(this.quoteAuthor).text('- ' + quote.author + ' -')
  }

  onUnload () {
    $(this.home).off('click')
    $(this.next).off('click')
  }
}
