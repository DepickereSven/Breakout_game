/**
 * @module userinput
 */

const { wsClient } = require('./socket/client')
const actions = require('./actions')

$('#create_game_btn').on('click', function() {
  wsClient.send(actions.createGameRequest())
})

$('#join_game_btn').on('click', function() {
  const key = $('#game_key_input').val()
  wsClient.send(actions.joinGameRequest(key))
})
