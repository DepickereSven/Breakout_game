const backgroundMusic = [[new Audio('music/Circles.mp3')], [new Audio('music/Firefly.mp3')], [new Audio('music/Invincible.mp3')], [new Audio('music/Limitless.mp3')]]

const sounds = {
  brickHit: new Audio('music/Hit_sound.mp3')
}

exports.fxSound = function (key) {
  if (!JSON.parse(localStorage.getItem('fxSound'))) {
    return
  }
  const snd = sounds[key]
  if (snd) {
    snd.play()
  }
}

exports.playBackground = function () {
  const snd = backgroundMusic[randomNumber()][0]
  snd.volume = (0.15)
  if (snd) {
    snd.play()
  }
  return snd
}

exports.stopBackgroudMusic = function (snd) {
  snd.pause()
  snd.currentTime = 0.0
  return undefined
}

exports.isBackgroundMusicPlaying = function (snd) {
  return !snd.paused
}

function randomNumber () {
  return Math.floor((Math.random() * 3))
}
