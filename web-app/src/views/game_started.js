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

function getDirection ({ touches }) {
  const xPos = touches[touches.length - 1].clientX
  return xPos > window.innerWidth / 2 ? 'right' : 'left'
}

function handleTouchStart (e) {
  e.preventDefault()
  const direction = getDirection(e)
  window.wsClient.send(new MovePaddleStartAction(direction))
  return false
}

function handleTouchEnd (e) {
  window.wsClient.send(new MovePaddleStopAction())
  if (e.touches.length > 0) {
    const direction = getDirection(e)
    window.wsClient.send(new MovePaddleStartAction(direction))
  }
}

const listenerOptions = { passive: false }
els.container[0].addEventListener(
  'touchstart',
  handleTouchStart,
  listenerOptions
)
els.container[0].addEventListener('touchend', handleTouchEnd, listenerOptions)
els.container[0].addEventListener(
  'touchcancel',
  handleTouchEnd,
  listenerOptions
)
