/**
 * Contains action creators
 * @module actions
 */

/**
 * Action object creator
 * @param {string} shortType
 * @param {object} payload
 */
function createAction (shortType, payload={}){
  const fullType = shortType + 'Action'
  return { type: fullType, ...payload }
}


exports.createGameRequest = () =>
  createAction('CreateGameRequest')

exports.joinGameRequest = (key) =>
  createAction('JoinGameRequest', { key })
