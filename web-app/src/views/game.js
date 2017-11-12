const { MovePaddleStartAction } = require('../actions/move_paddle_start')
const { MovePaddleStopAction } = require('../actions/move_paddle_stop')

const { createSketch } = require('../sketch')
const { GameLoop } = require('../gameloop')

const path = 'game.html'
exports.path = path

exports.view = class GameView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true
    this.currentCount = undefined

    this.container = 'game_container'
    this.keyCodePressed = undefined
    this.countDownOverlay = '.countdown_overlay'
    this.countDownCount = '#countdown_count'

    this.handleTouchStart = this.handleTouchStart.bind(this)
    this.countDown = this.countDown.bind(this)
  }

  countDown (count) {
    if (count === this.currentCount) {
      return
    }
    if (count === 0) {
      $(this.countDownOverlay).addClass('hidden')
    }
    $(this.countDownCount).text(count)
    this.currentCount = count
  }

  getDirection ({ touches }) {
    const xPos = touches[touches.length - 1].pageX
    return xPos > window.innerWidth / 2 ? 'right' : 'left'
  }

  handleTouchStart (e) {
    e.preventDefault()
    const direction = this.getDirection(e)
    window.wsClient.send(new MovePaddleStartAction(direction))
    return false
  }

  handleTouchEnd (e) {
    window.wsClient.send(new MovePaddleStopAction())
  }

  handleKeyDown (e) {
    if (this.keyCodePressed === e.keyCode) {
      return
    }
    this.keyCodePressed = e.keyCode

    switch (e.key) {
      case 'ArrowLeft':
        window.wsClient.send(new MovePaddleStartAction('left'))
        break
      case 'ArrowRight':
        window.wsClient.send(new MovePaddleStartAction('right'))
        break
    }
  }

  handleKeyUp (e) {
    this.keyCodePressed = undefined
    window.wsClient.send(new MovePaddleStopAction())
  }

  onLoad () {
    const container = document.getElementById(this.container)
    const options = { passive: false }

    const sketch = createSketch()
    window.gameLoop = new GameLoop(sketch)

    container.addEventListener('touchstart', this.handleTouchStart, options)
    container.addEventListener('touchend', this.handleTouchEnd, options)
    container.addEventListener('touchcancel', this.handleTouchEnd, options)

    $(window).on('keydown', this.handleKeyDown)
    $(window).on('keyup', this.handleKeyUp)
  }

  onUnload () {
    const container = document.getElementById(this.container)
    container.removeEventListener('touchstart', this.handleTouchStart)
    container.removeEventListener('touchend', this.handleTouchEnd)
    container.removeEventListener('touchcancel', this.handleTouchEnd)

    $(window).off('keydown', this.handleKeyDown)
    $(window).off('keyup', this.handleKeyUp)
  }
}
