const music = require('./music')
const state = require('./global_state')
let snd

exports.playMusic = function () {
  if (!state.get('gameSound')) {
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
