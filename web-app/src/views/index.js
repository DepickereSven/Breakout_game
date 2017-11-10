const modeScreen = require('./mode_screen')
const multiplayerScreen = require('./multiplayer_screen')
const createMultiplayerScreen = require('./create_multiplayer_screen')

const viewsMap = {
  [modeScreen.path]: modeScreen.ModeScreenView,
  [multiplayerScreen.path]: multiplayerScreen.MultiplayerScreenView,
  [createMultiplayerScreen.path]: createMultiplayerScreen.CreateMultiplayerScreenView
}
exports.viewsMap = viewsMap

class ViewManager {
  constructor () {
    this.viewHistory = []
  }

  go (path) {
    const ViewConstructor = viewsMap[path]

    if (!ViewConstructor) {
      throw new Error(`View "${path}" doesn't exist.`)
    }

    const view = new ViewConstructor(this)

    $.ajax({
      url: path
    }).done(html => {
      const currentView = this.viewHistory[this.viewHistory.length - 1]
      if (currentView) {
        currentView.onUnload()
      }
      $('.screen').removeClass('currentScreen')
      $(document.body).append(`<div class="screen">${html}</div>`)

      setTimeout(function () {
        $('.screen')
          .last()
          .addClass('currentScreen')
        $('.screen:not(.currentScreen)').remove()
      }, 100)

      view.onLoad()

      this.viewHistory.push(view)
    })
  }
}

exports.viewManager = new ViewManager()
