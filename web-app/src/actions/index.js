const { ConnectionSuccessAction } = require('./connection_success')
const { CreateGameSuccessAction } = require('./create_game_success')
const { GameStartAction } = require('./game_start')
const { GameStopAction } = require('./game_stop')
const { GameVictoryAction } = require('./game_victory')
const { GameLossAction } = require('./game_loss')
const { GameStateUpdateAction } = require('./game_state_update')

exports.requestActionsMap = {
  ConnectionSuccessAction,
  CreateGameSuccessAction,
  GameStartAction,
  GameStopAction,
  GameVictoryAction,
  GameLossAction,
  GameStateUpdateAction
}
