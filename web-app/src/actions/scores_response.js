/**
 * @module actions/scores_response.js
 */
const state = require('../global_state')

exports.handler = function ({ scores }) {
  const view = viewManager.getPreviousView()
  if (view.setScores) {
    state.set('stats', true)
    window.viewManager.go('stats.html')
    view.setScores(scores)
  }
}
