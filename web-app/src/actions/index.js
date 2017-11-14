const ConnectionSuccessAction = require('./connection_success')
const CreateSingleplayerSuccessAction = require('./create_singleplayer_success')
const CreateMultiplayerSuccessAction = require('./create_multiplayer_success')
const GameStartAction = require('./game_start')
const GameStopAction = require('./game_stop')
const GameVictoryAction = require('./game_victory')
const GameLossAction = require('./game_loss')
const GameStateUpdateAction = require('./game_state_update')

exports.requestActionsMap = {
  ConnectionSuccessAction,
  CreateSingleplayerSuccessAction,
  CreateMultiplayerSuccessAction,
  GameStartAction,
  GameStopAction,
  GameVictoryAction,
  GameLossAction,
  U: GameStateUpdateAction
}
