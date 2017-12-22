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
    this.screen = '.gamemodes-container'
    this.generateLevels = this.generateLevels.bind(this)
    this.handleLevelClick = this.handleLevelClick.bind(this)
  }

  generateLevels (min, max) {
    $(this.container).html('')
    let items = []
    for (max; min <= max; min++) {
      items.push(`
        <button class="level rounded" data-level="${min}">
          <span class="levelName">${min}</span>
        </button>
      `)
    }
    console.log(max)
    if (max === 10 || max === 25 || max === 40) {
      $(this.container).css('width', '500px')
    }
    $(this.container).append(items)
  }

  handleLevelClick (e) {
    let { level } = $(e.currentTarget).data()
    level = parseInt(level)
    state.set('currentLevel', level)
    this.viewManager.go('singleplayer_score.html')
  }

  controleIfBlur () {
    let $screen = $(this.screen).parent()
    if ($screen.hasClass('blur')) {
      $screen.removeClass('blur')
    }
  }

  onLoad () {
    this.generateLevels(this.min, this.max)
    $(this.levelButton).on('click', this.handleLevelClick)
    this.controleIfBlur()
  }

  onUnload () {
    $(this.levelButton).off('click')
  }
}
