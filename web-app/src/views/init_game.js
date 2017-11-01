/**
 * @module views/init_game
 */

const { wsClient } = require('../socket/client')
const actions = require('../actions')
const { showView } = require('../utils')

const els = {
  container: $('#init_game_modal'),
  createGameBtn: $('#create_game_btn'),
  joinGameBtn: $('#join_game_btn'),
  gameKeyInput: $('#join_game_input')
}


exports.show = function show() {
  showView(els.container)
}


els.createGameBtn.on('click', function() {
  wsClient.send(actions.createGameRequest())
})


els.joinGameBtn.on('click', function() {
  const key = gameKeyInput.val()
  wsClient.send(actions.joinGameRequest(key))
})
