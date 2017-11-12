function select () {
  const val = $(this).attr('data-val')
  playMusic(val)
}

function playMusic (audioName) {
  const snd = new Audio('music/' + audioName + '.mp3')
  snd.play()
}

$('.click_me').on('click', select())
