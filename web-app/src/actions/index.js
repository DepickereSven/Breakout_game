const ConnectionSuccessAction = require('./connection_success')
const CreateSingleplayerSuccessAction = require('./create_singleplayer_success')
const CreateMultiplayerSuccessAction = require('./create_multiplayer_success')
const GameStartAction = require('./game_start')
const GameStopAction = require('./game_stop')
const GameVictoryAction = require('./game_victory')
const GameLossAction = require('./game_loss')
const GameStateUpdateAction = require('./game_state_update')
const UserLoginSuccessAction = require('./user_login_success')
const UserLoginFailureAction = require('./user_login_failure')
const ScoresResponseAction = require('./scores_request')

exports.responseActionsMap = {
  ConnectionSuccessAction,
  CreateSingleplayerSuccessAction,
  CreateMultiplayerSuccessAction,
  GameStartAction,
  GameStopAction,
  GameVictoryAction,
  GameLossAction,
  U: GameStateUpdateAction,
  UserLoginSuccessAction,
  UserLoginFailureAction,
  ScoresResponseAction
}