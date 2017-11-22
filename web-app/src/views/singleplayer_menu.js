const path = 'singleplayer_menu.html'
exports.path = path

exports.view = class SingleplayerMenuView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.levelButtons = 'button.diff'
  }

  handleLevelClick (e) {
    const data = $(e.currentTarget).data()
    this.viewManager.go('singleplayer_level_menu.html', data)
  }
  rememberOnWhichGameModeYouAre () {
    localStorage.setItem('whatMode', JSON.stringify('single'))
  }
  onLoad () {
    this.rememberOnWhichGameModeYouAre()
    $(this.levelButtons).on('click', this.handleLevelClick.bind(this))
  }

  onUnload () {
    $(this.levelButtons).off('click')
  }
}
