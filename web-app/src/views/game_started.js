/**
 * @module views/game_started
 */

const { showView } = require('../utils')
const { MovePaddleStartAction } = require('../actions/move_paddle_start')
const { MovePaddleStopAction } = require('../actions/move_paddle_stop')

const els = {
  container: $('#game_started_view')
}

exports.show = function show () {
  showView(els.container)
  let keyCodePressed

  $(window).on('keydown', function (e) {
    if (keyCodePressed === e.keyCode) {
      return
    }
    keyCodePressed = e.keyCode

    switch (e.key) {
      case 'ArrowLeft':
        window.wsClient.send(new MovePaddleStartAction('left'))
        break
      case 'ArrowRight':
        window.wsClient.send(new MovePaddleStartAction('right'))
        break
    }
  })

  $(window).on('keyup', function (e) {
    keyCodePressed = undefined
    window.wsClient.send(new MovePaddleStopAction())
  })
}
