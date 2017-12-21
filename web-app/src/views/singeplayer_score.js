const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')
const state = require('../global_state.js')

const path = 'singleplayer_level_menu.html'
exports.path = path

exports.view = class SingleplayerLevelMenuView {
  constructor (viewManager, { min, max }) {
    this.path = path
    this.viewManager = viewManager
    this.min = min
    this.max = max

    this.container = '.singleplayer_level_menu'
    this.levelButton = 'button.level'

    this.generateScoresForLevel = this.generateScoresForLevel.bind(this)
    this.handleLevelClick = this.handleLevelClick.bind(this)
  }
  generateScoresForLevel (min, max) {
    let items = []
    for (max; min <= max; min++) {
      items.push(`
        <button class="level rounded" data-level="${min}">
          <span class="levelName">${min}</span>
        </button>
      `)
    }
    $(this.container).append(items)
  }
  getLevelHighScore (level) {
    let scores = JSON.parse(localStorage.getItem(level))
    if (scores === null) {
      return 'NO HIGH SCORES'
    } else {
      return scores
    }
  }
  handleLevelClick (e) {
    this.viewManager.go('loading.html')
    let { level } = $(e.currentTarget).data()
    level = parseInt(level)
    state.set('currentLevel', level)
    window.wsClient.send(CreateSingleplayerRequestAction.create(level))
  }

  onLoad () {
    this.generateScoresForLevel()
    $(this.levelButton).on('click', this.handleLevelClick)
  }

  onUnload () {
    $(this.levelButton).off('click')
  }
}
