const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')

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

    this.generateLevels = this.generateLevels.bind(this)
    this.handleLevelClick = this.handleLevelClick.bind(this)
  }

  generateLevels (min, max) {
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

  handleLevelClick (e) {
    this.viewManager.go('loading.html')
    const { level } = $(e.currentTarget).data()
    window.wsClient.send(CreateSingleplayerRequestAction.create(parseInt(level)))
  }

  onLoad () {
    this.generateLevels(this.min, this.max)
    $(this.levelButton).on('click', this.handleLevelClick)
  }

  onUnload () {
    $(this.levelButton).off('click')
  }
}
