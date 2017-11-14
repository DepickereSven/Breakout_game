exports.playMusic = function (audioName) {
  const snd = new Audio('music/' + audioName + '.mp3')
  console.log(snd, audioName)
  snd.play()
}
