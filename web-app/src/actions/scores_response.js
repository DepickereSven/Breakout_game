/**
 * @module actions/scores_response.js
 */

exports.handler = function ( { scores }) {
  console.log(scores)//TODO temp
  const view = viewManager.getCurrentView()
  if (view.setScores) {
    view.setScores(scores)
  }
}
