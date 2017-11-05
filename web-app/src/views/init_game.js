/**
 * @module views/init_game
 */

const { showView } = require('../utils')
const { CreateGameRequestAction } = require('../actions/create_game_request')
const { JoinGameRequestAction } = require('../actions/join_game_request')

const els = {
  container: $('#init_game_view'),
  createGameBtn: $('#create_game_btn'),
  joinGameBtn: $('#join_game_btn'),
  gameKeyInput: $('#game_key_input')
}

exports.show = function show () {
  showView(els.container)

  els.createGameBtn.on('click', function () {
    window.wsClient.send(new CreateGameRequestAction())
  })

  els.joinGameBtn.on('click', function () {
    const key = els.gameKeyInput.val()
    window.wsClient.send(new JoinGameRequestAction(key))
  })
}
