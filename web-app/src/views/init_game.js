/**
 * @module views/init_game
 */

const { showView } = require('../utils')
const { CreateMultiplayerRequestAction } = require('../actions/create_multiplayer_request')
const { CreateSingleplayerRequestAction } = require('../actions/create_singleplayer_request')
const { JoinGameRequestAction } = require('../actions/join_game_request')

const els = {
  container: $('#init_game_view'),
  createMultiplayerBtn: $('#create_multiplayer_btn'),
  createSingleplayerBtn: $('#create_singleplayer_btn'),
  joinGameBtn: $('#join_game_btn'),
  gameKeyInput: $('#game_key_input')
}

exports.show = function show () {
  showView(els.container)

  els.createMultiplayerBtn.on('click', function () {
    window.wsClient.send(new CreateMultiplayerRequestAction())
  })
  
  els.createSingleplayerBtn.on('click', function () {
    window.wsClient.send(new CreateSingleplayerRequestAction())
  })

  els.joinGameBtn.on('click', function () {
    const key = els.gameKeyInput.val()
    window.wsClient.send(new JoinGameRequestAction(key))
  })
}
