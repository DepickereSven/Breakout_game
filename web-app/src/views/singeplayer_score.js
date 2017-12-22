const CreateSingleplayerRequestAction = require('../actions/create_singleplayer_request')
const state = require('../global_state.js')

const path = 'singleplayer_score.html'
exports.path = path

exports.view = class SingleplayerLevelMenuView {
  constructor (viewManager, { min, max }) {
    this.path = path
    this.viewManager = viewManager
    this.min = min
    this.max = max

    this.tbody = 'tbody'
    this.levelButton = 'button.btn'
    this.container = '.table-level-score'
    this.screen = '.container-score'

    this.generateScoresForLevel = this.generateScoresForLevel.bind(this)
    this.handleLevelClick = this.handleLevelClick.bind(this)
  }
  generateScoresForLevel () {
    let scores = this.getLevelHighScore(state.get('currentLevel'))
    let items = []
    let max = 0
    if (typeof scores === 'string') {
      $(this.container).parent().append('<p>' + scores + '</p>')
    } else {
      for (max; max < scores.length; max++) {
        items.push(`
        <tr>
            <td>${scores[max][0]}</td>
            <td>${scores[max][1]}</td>
        </tr>
      `)
      }
      $(this.tbody).append(items)
    }
    $(this.screen)
      .parent()
      .addClass('transparent')
      .prev().addClass('blur')
  }
  getLevelHighScore (level) {
    let scores = state.get('singleScore')
    let score = scores[level]
    if (score === undefined) {
      return 'NO HIGH SCORES'
    } else {
      return this.sortTheArrayToGoingUp(score)
    }
  }
  handleLevelClick (e) {
    this.viewManager.go('loading.html')
    let level = state.get('currentLevel')
    window.wsClient.send(CreateSingleplayerRequestAction.create(level))
  }
  sortTheArrayToGoingUp (score) {
    return score.sort(function (a, b) {
      return a[0] - b[0]
    })
  }

  onLoad () {
    this.generateScoresForLevel()
    $(this.levelButton).on('click', this.handleLevelClick)
  }

  onUnload () {
    $(this.levelButton).off('click')
  }
}
