const MovePaddleStartAction = require('../actions/move_paddle_start')
const MovePaddleStopAction = require('../actions/move_paddle_stop')

const { createSketch } = require('../sketch')
const utils = require('../utils')

const path = 'game.html'
exports.path = path

exports.view = class GameView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true

    this.currentCount = 5
    this.currentTime = 0

    this.container = 'game_container'
    this.keyCodePressed = undefined

    this.countDownOverlay = '.countdown_overlay'
    this.countDownCount = '#countdown_count'
    this.time = '#time'

    this.handleTouchStart = this.handleTouchStart.bind(this)
    this.setCount = this.setCount.bind(this)
    this.setTime = this.setTime.bind(this)
  }

  setCount (count) {
    if (count === this.currentCount) {
      return
    }
    if (count === 0) {
      $(this.countDownOverlay).addClass('hidden')
    }
    $(this.countDownCount).text(count)
    this.currentCount = count
  }

  setTime (time) {
    if (time === this.currentTime) {
      return
    }
    this.currentTime = time
    $(this.time).text(utils.displayTime(time))
  }

  getDirection ({ touches }) {
    const xPos = touches[touches.length - 1].pageX
    return xPos > window.innerWidth / 2 ? 'right' : 'left'
  }

  handleTouchStart (e) {
    e.preventDefault()
    const direction = this.getDirection(e)
    window.wsClient.send(MovePaddleStartAction.create(direction))
    return false
  }

  handleTouchEnd (e) {
    window.wsClient.send(MovePaddleStopAction.create())
  }

  handleKeyDown (e) {
    if (this.keyCodePressed === e.keyCode) {
      return
    }
    this.keyCodePressed = e.keyCode

    switch (e.key) {
      case 'ArrowLeft':
        window.wsClient.send(MovePaddleStartAction.create('left'))
        break
      case 'ArrowRight':
        window.wsClient.send(MovePaddleStartAction.create('right'))
        break
    }
  }

  handleKeyUp (e) {
    this.keyCodePressed = undefined
    window.wsClient.send(MovePaddleStopAction.create())
  }

  onLoad () {
    createSketch()

    const container = document.getElementById(this.container)
    const options = { passive: false }
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

    $(container).find('canvas').remove()
  }
}
