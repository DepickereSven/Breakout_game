const music = require('./music')
const constants = require('./constants')
const state = require('./global_state')
let snd

exports.playMusic = function () {
  music.controleState(state.get('fxSound'))
  if (constants.IS_SAFARI || !state.get('gameSound')) {
    return
  }
  snd = music.playBackground()
  snd.onended = function () {
    snd = music.playBackground()
  }
}

exports.stopTheMusic = function () {
  if (snd === undefined) {
  } else {
    snd = music.stopBackgroudMusic(snd)
  }
}
