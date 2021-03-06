const path = 'singleplayer_menu.html'
exports.path = path

exports.view = class SingleplayerMenuView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.levelButtons = 'button.diff'
    this.tutorial = '.tut'
  }

  handleLevelClick (e) {
    const data = $(e.currentTarget).data()
    this.viewManager.go('singleplayer_level_menu.html', data)
  }
  sendToTurial (e) {
    this.viewManager.go('tutorialOverview.html')
  }
  onLoad () {
    $(this.levelButtons).on('click', this.handleLevelClick.bind(this))
    $(this.tutorial).on('click', this.sendToTurial.bind(this))
  }

  onUnload () {
    $(this.levelButtons).off('click')
    $(this.tutorial).off('click')
  }
}
