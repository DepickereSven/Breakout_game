const state = require('../global_state')

const path = 'sound_setting.html'
exports.path = path

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.viewManager = viewManager

    this.gameSound = '#game_sound'
    this.fxSound = '#fx_sound'

    this.saveStatusGameSound = this.saveStatusGameSound.bind(this)
    this.saveStatusFxSound = this.saveStatusFxSound.bind(this)
  }

  saveStatusGameSound () {
    state.set('gameSound', $(this.gameSound).is(':checked'))
  }

  saveStatusFxSound () {
    state.set('fxSound', $(this.fxSound).is(':checked'))
  }

  onLoad () {
    $(this.gameSound).prop('checked', state.get('gameSound'))
    $(this.fxSound).prop('checked', state.get('fxSound'))

    $(this.gameSound).on('click', this.saveStatusGameSound)
    $(this.fxSound).on('click', this.saveStatusFxSound)
  }

  onUnload () {
    $(this.gameSound).off('click')
    $(this.fxSound).off('click')
  }
}
