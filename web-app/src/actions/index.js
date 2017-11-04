const { CreateGameSuccessAction } = require('./create_game_success')
const { GameStartAction } = require('./game_start')
const { GameStopAction } = require('./game_stop')
const { GameStateUpdateAction } = require('./game_state_update')

exports.requestActionsMap = {
  CreateGameSuccessAction,
  GameStartAction,
  GameStopAction,
  GameStateUpdateAction
}
