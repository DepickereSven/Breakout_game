const state = require('../global_state.js')

exports.updateScores = function (level, score) {
  let scores = state.get('singleScore')
  if (scores === undefined) {
    scores = {}
  }
  if (scores[level] === undefined) {
    scores[level] = [[score, generateTime()]]
  } else {
    scores[level].push([score, generateTime()])
  }
  state.set('singleScore', scores)
}

function generateTime () {
  let today = new Date()
  let dd = today.getDate()
  let mm = today.getMonth()
  let yyyy = today.getFullYear()
  if (dd < 10) {
    dd = '0' + dd
  } if (mm < 10) {
    mm = '0' + mm
  }
  return dd + '/' + mm + '/' + yyyy
}
