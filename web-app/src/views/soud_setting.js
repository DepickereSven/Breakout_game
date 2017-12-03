const path = 'sound_setting.html'
exports.path = path

exports.view = class LoadingView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.viewManager = viewManager
    this.remove = true
    this.gameSound = '#game_sound'
    this.fxSound = '#fx_sound'
  }

  getTheStatusOfTheSounds () {
    $(this.gameSound).prop('checked', JSON.parse(localStorage.getItem('gameSound')))
    $(this.fxSound).prop('checked', JSON.parse(localStorage.getItem('fxSound')))
  }

  saveStatusGameSound () {
    localStorage.setItem('gameSound', JSON.stringify($(this).is(':checked')))
  }

  saveStatusFxSound () {
    localStorage.setItem('fxSound', JSON.stringify($(this).is(':checked')))
  }
  onLoad () {
    this.getTheStatusOfTheSounds()
    $(this.gameSound).on('click', this.saveStatusGameSound)
    $(this.fxSound).on('click', this.saveStatusFxSound)
  }

  onUnload () {
    $(this.gameSound).off('click')
    $(this.fxSound).off('click')
  }
}
