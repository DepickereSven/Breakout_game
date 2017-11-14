
exports.playMusic = function (audioName) {
  const snd = new Audio('music/' + audioName + '.mp3')
  snd.play()
}
