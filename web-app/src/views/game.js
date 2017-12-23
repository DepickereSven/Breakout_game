const MovePaddleStartAction = require('../actions/move_paddle_start')
const MovePaddleStopAction = require('../actions/move_paddle_stop')

const { createSketch } = require('../sketch')
const { powerTypes, getPowerImg } = require('../powers')
const utils = require('../utils')
const constants = require('../constants')
const backgroundMusic = require('../music_controller')

const path = 'game.html'
exports.path = path

const photoArrayAndroid = ['A1.jpg', 'A2.jpg', 'A3.jpg']
const phototArrayPC = ['PC1.jpeg', 'PC2.jpg']

exports.view = class GameView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
    this.hideHeader = true
    this.remove = true

    this.currentCount = 5
    this.currentTime = 0

    this.container = '#game_container'
    this.keyCodePressed = undefined

    this.countDownOverlay = '.countdown_overlay'
    this.countDownCount = '#countdown_count'
    this.time = '#time'
    this.powers = '#powers'
    this.powerImgs = '#powers > img'

    this.handleTouchStart = this.handleTouchStart.bind(this)
    this.setCount = this.setCount.bind(this)
    this.setTime = this.setTime.bind(this)
    this.setPowers = this.setPowers.bind(this)
  }

  setCount (count) {
    if (count === this.currentCount) {
      return
    }
    if (count === 0) {
      $(this.countDownOverlay).addClass('hidden')
      backgroundMusic.playMusic()
    }
    $(this.countDownCount).text(count)
    this.currentCount = count
  }

  setTime (time) {
    this.currentTime = time
    $(this.time).text(utils.displayTime(time))
  }

  setPowers (powers) {
    this.powerImgEls.addClass('hidden')
    powers.forEach((x) => {
      const el = this.powerImgEls[x - 1]
      if (el) {
        el.className = ''
      }
    })
  }

  insertPowersImgs () {
    const powerImgEls = powerTypes.map((x, i) => `<img src="${getPowerImg(i + 1)}" class="hidden">`)
    $(this.powers).append(powerImgEls)
    this.powerImgEls = $(this.powerImgs)
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
      case 'Left':
        window.wsClient.send(MovePaddleStartAction.create('left'))
        break
      case 'ArrowRight':
      case 'Right':
        window.wsClient.send(MovePaddleStartAction.create('right'))
        break
    }
  }

  handleKeyUp (e) {
    this.keyCodePressed = undefined
    window.wsClient.send(MovePaddleStopAction.create())
  }
  setGameBackground () {
    let photo = ''
    if (constants.IS_ANDROID_APP) {
      photo = photoArrayAndroid[utils.randomInRange(0, photoArrayAndroid.length - 1)]
    } else {
      photo = phototArrayPC[utils.randomInRange(0, phototArrayPC.length - 1)]
    }
    $('#game_container')
      .parent().css({
        'background-image': `url(images/backgrounds/${photo})`,
        'background-size': 'cover',
        'background-position': 'center'
      })
  }
  onLoad () {
    createSketch()
    this.setGameBackground()
    const options = { passive: false }
    document.body.addEventListener('touchstart', this.handleTouchStart, options)
    document.body.addEventListener('touchend', this.handleTouchEnd, options)
    document.body.addEventListener('touchcancel', this.handleTouchEnd, options)

    $(window).on('keydown', this.handleKeyDown)
    $(window).on('keyup', this.handleKeyUp)

    setTimeout(this.insertPowersImgs(), 100)
  }

  onUnload () {
    document.body.removeEventListener('touchstart', this.handleTouchStart)
    document.body.removeEventListener('touchend', this.handleTouchEnd)
    document.body.removeEventListener('touchcancel', this.handleTouchEnd)

    $(window).off('keydown', this.handleKeyDown)
    $(window).off('keyup', this.handleKeyUp)

    $(this.container).find('canvas').remove()
  }
}
