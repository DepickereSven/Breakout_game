/**
 * @module actions/scores_response.js
 */

exports.handler = function ( { scores }) {
  const view = viewManager.getCurrentView()
  if (view.setScores) {
    view.setScores(scores)
  }
}
