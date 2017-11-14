const constants = require('./constants')

const sounds = {
  brickHit: new Audio('music/1.mp3')
}

exports.play = function (key) {
  if (!constants.IS_ANDROID_APP) {
    return
  }
  const snd = sounds[key]
  if (snd) {
    snd.play()
  }
}
