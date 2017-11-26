const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')
const state = require('../global_state.js')
const { LOSS_QUOTES } = require('../constants.js')
const utils = require('../utils')

const path = 'singleplayer_game_lost.html'
exports.path = path

exports.view = class PickModeView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = true
    this.remove = true

    this.viewManager = viewManager
    this.retry = '#retry_singleplayer'
    this.home = '#home'
    this.quoteText = '#quote-text'
    this.quoteAuthor = '#quote-author'

    this.goHome = this.goHome.bind(this)
    this.handleRetryClick = this.handleRetryClick.bind(this)
  }

  handleRetryClick () {
    this.viewManager.go('loading.html')
    const level = state.get('currentLevel')
    window.wsClient.send(CreateSingleplayerRequestAction.create(level))
  }
  goHome () {
    this.viewManager.goHome()
  }

  onLoad () {
    $(this.retry).on('click', this.handleRetryClick)
    $(this.home).on('click', this.goHome)

    const quote = LOSS_QUOTES[utils.randomInRange(0, LOSS_QUOTES.length - 1)]
    $(this.quoteText).text(quote.text)
    $(this.quoteAuthor).text('- ' + quote.author + ' -')
  }

  onUnload () {
    $(this.retry).off('click')
    $(this.home).off('click')
  }
}
