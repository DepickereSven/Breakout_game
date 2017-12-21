const music = require('./music')

let snd

exports.playMusic = function () {
  if (!JSON.parse(localStorage.getItem('gameSound'))) {
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
