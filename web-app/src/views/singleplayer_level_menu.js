const { CreateSingleplayerRequestAction } = require('../actions/create_singleplayer_request')

const path = 'singleplayer_level_menu.html'
exports.path = path

exports.view = class SingleplayerMenuView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.container = '.singleplayer_level_menu'
    this.levelButton = '.level'
  }

  generateLevels (min, max) {
    let items = []
    for (max; min <= max; min++) {
      items.push(`
        <a class="level rounded" data-level="${min}">
          <span class="levelName">${min}</span>
        </a>
      `)
    }
    $(this.container).append(items)
  }

  handleLevelClick (e) {
    const { level } = $(e.currentTarget).data()
    window.wsClient.send(new CreateSingleplayerRequestAction())
  }

  onLoad ({ min, max }) {
    this.generateLevels(min, max)
    $(this.levelButton).on('click', this.handleLevelClick)
  }

  onUnload () {}
}
