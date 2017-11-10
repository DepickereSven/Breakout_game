(function() {
  'use strict';

  var globals = typeof global === 'undefined' ? self : global;
  if (typeof globals.require === 'function') return;

  var modules = {};
  var cache = {};
  var aliases = {};
  var has = {}.hasOwnProperty;

  var expRe = /^\.\.?(\/|$)/;
  var expand = function(root, name) {
    var results = [], part;
    var parts = (expRe.test(name) ? root + '/' + name : name).split('/');
    for (var i = 0, length = parts.length; i < length; i++) {
      part = parts[i];
      if (part === '..') {
        results.pop();
      } else if (part !== '.' && part !== '') {
        results.push(part);
      }
    }
    return results.join('/');
  };

  var dirname = function(path) {
    return path.split('/').slice(0, -1).join('/');
  };

  var localRequire = function(path) {
    return function expanded(name) {
      var absolute = expand(dirname(path), name);
      return globals.require(absolute, path);
    };
  };

  var initModule = function(name, definition) {
    var hot = hmr && hmr.createHot(name);
    var module = {id: name, exports: {}, hot: hot};
    cache[name] = module;
    definition(module.exports, localRequire(name), module);
    return module.exports;
  };

  var expandAlias = function(name) {
    return aliases[name] ? expandAlias(aliases[name]) : name;
  };

  var _resolve = function(name, dep) {
    return expandAlias(expand(dirname(name), dep));
  };

  var require = function(name, loaderPath) {
    if (loaderPath == null) loaderPath = '/';
    var path = expandAlias(name);

    if (has.call(cache, path)) return cache[path].exports;
    if (has.call(modules, path)) return initModule(path, modules[path]);

    throw new Error("Cannot find module '" + name + "' from '" + loaderPath + "'");
  };

  require.alias = function(from, to) {
    aliases[to] = from;
  };

  var extRe = /\.[^.\/]+$/;
  var indexRe = /\/index(\.[^\/]+)?$/;
  var addExtensions = function(bundle) {
    if (extRe.test(bundle)) {
      var alias = bundle.replace(extRe, '');
      if (!has.call(aliases, alias) || aliases[alias].replace(extRe, '') === alias + '/index') {
        aliases[alias] = bundle;
      }
    }

    if (indexRe.test(bundle)) {
      var iAlias = bundle.replace(indexRe, '');
      if (!has.call(aliases, iAlias)) {
        aliases[iAlias] = bundle;
      }
    }
  };

  require.register = require.define = function(bundle, fn) {
    if (bundle && typeof bundle === 'object') {
      for (var key in bundle) {
        if (has.call(bundle, key)) {
          require.register(key, bundle[key]);
        }
      }
    } else {
      modules[bundle] = fn;
      delete cache[bundle];
      addExtensions(bundle);
    }
  };

  require.list = function() {
    var list = [];
    for (var item in modules) {
      if (has.call(modules, item)) {
        list.push(item);
      }
    }
    return list;
  };

  var hmr = globals._hmr && new globals._hmr(_resolve, require, modules, cache);
  require._cache = cache;
  require.hmr = hmr && hmr.wrap;
  require.brunch = true;
  globals.require = require;
})();

(function() {
var global = typeof window === 'undefined' ? this : window;
var process;
var __makeRelativeRequire = function(require, mappings, pref) {
  var none = {};
  var tryReq = function(name, pref) {
    var val;
    try {
      val = require(pref + '/node_modules/' + name);
      return val;
    } catch (e) {
      if (e.toString().indexOf('Cannot find module') === -1) {
        throw e;
      }

      if (pref.indexOf('node_modules') !== -1) {
        var s = pref.split('/');
        var i = s.lastIndexOf('node_modules');
        var newPref = s.slice(0, i).join('/');
        return tryReq(name, newPref);
      }
    }
    return none;
  };
  return function(name) {
    if (name in mappings) name = mappings[name];
    if (!name) return;
    if (name[0] !== '.' && pref) {
      var val = tryReq(name, pref);
      if (val !== none) return val;
    }
    return require(name);
  }
};
require.register("src/actions/connection_success.js", function(exports, require, module) {
/**
 * @module actions/connection_success.js
 */

exports.ConnectionSuccessAction = class ConnectionSuccessAction {
  constructor ({ clientId }) {
    this.clientId = clientId
  }

  handler () {
    window.wsClient.setClientId(this.clientId)
  }
}

});

;require.register("src/actions/create_multiplayer_request.js", function(exports, require, module) {
/**
 * @module actions/create_game_request.js
 */

exports.CreateMultiplayerRequestAction = class CreateMultiplayerRequestAction {}

});

;require.register("src/actions/create_multiplayer_success.js", function(exports, require, module) {
/**
 * @module actions/create_game_success.js
 */

const { viewManager } = require('../views/index')

exports.CreateMultiplayerSuccessAction = class CreateMultiplayerSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {
    //viewManager.go('')
  }
}

});

;require.register("src/actions/create_singleplayer_request.js", function(exports, require, module) {
/**
 * @module actions/create_game_request.js
 */

exports.CreateSingleplayerRequestAction = class CreateSingleplayerRequestAction {}

});

;require.register("src/actions/create_singleplayer_success.js", function(exports, require, module) {
/**
 * @module actions/create_game_success.js
 */

const createdGameView = require('../views/created_game')

exports.CreateSingleplayerSuccessAction = class CreateSingleplayerSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {

  }
}

});

;require.register("src/actions/game_loss.js", function(exports, require, module) {
/**
 * @module actions/game_loss.js
 */

const gameLossView = require('../views/game_loss')

exports.GameLossAction = class GameLossAction {
  handler () {
    gameLossView.show()
  }
}

});

;require.register("src/actions/game_start.js", function(exports, require, module) {
/**
 * @module actions/game_start.js
 */

const gameStartedView = require('../views/game_started')

exports.GameStartAction = class GameStartAction {
  handler () {
    gameStartedView.show()
  }
}

});

;require.register("src/actions/game_state_update.js", function(exports, require, module) {
/**
 * @module actions/game_state_update.js
 */

const { gameLoop } = require('../gameloop')

exports.GameStateUpdateAction = class GameStateUpdateAction {
  constructor ({ bodies, players }) {
    this.bodies = bodies
    this.players = players
  }

  handler () {
    gameLoop.updatePlayers(this.players)
    gameLoop.updateBodies(this.bodies)
  }
}

});

;require.register("src/actions/game_stop.js", function(exports, require, module) {
/**
 * @module actions/game_stop.js
 */

const gameStoppedView = require('../views/game_stopped')

exports.GameStopAction = class GameStopAction {
  handler () {
    gameStoppedView.show()
  }
}

});

;require.register("src/actions/game_victory.js", function(exports, require, module) {
/**
 * @module actions/game_victory.js
 */

const gameVictoryView = require('../views/game_victory')

exports.GameVictoryAction = class GameVictoryAction {
  handler () {
    gameVictoryView.show()
  }
}

});

;require.register("src/actions/index.js", function(exports, require, module) {
const { ConnectionSuccessAction } = require('./connection_success')
const { CreateSingleplayerSuccessAction } = require('./create_singleplayer_success')
const { CreateMultiplayerSuccessAction } = require('./create_multiplayer_success')
const { GameStartAction } = require('./game_start')
const { GameStopAction } = require('./game_stop')
const { GameVictoryAction } = require('./game_victory')
const { GameLossAction } = require('./game_loss')
const { GameStateUpdateAction } = require('./game_state_update')

exports.requestActionsMap = {
  ConnectionSuccessAction,
  CreateSingleplayerSuccessAction,
  CreateMultiplayerSuccessAction,
  GameStartAction,
  GameStopAction,
  GameVictoryAction,
  GameLossAction,
  GameStateUpdateAction
}

});

;require.register("src/actions/join_game_request.js", function(exports, require, module) {
/**
 * @module actions/join_game_request.js
 */

exports.JoinGameRequestAction = class JoinGameRequestAction {
  /**
   * @param {string} key 
   */
  constructor (key) {
    this.key = key
  }
}

});

;require.register("src/actions/move_paddle_start.js", function(exports, require, module) {
/**
 * @module actions/move_paddle_start.js
 */

exports.MovePaddleStartAction = class MovePaddleStartAction {
  constructor (direction) {
    this.direction = direction
  }
}

});

;require.register("src/actions/move_paddle_stop.js", function(exports, require, module) {
/**
 * @module actions/move_paddle_stop.js
 */

exports.MovePaddleStopAction = class MovePaddleStopAction {}

});

;require.register("src/bodies/ball.js", function(exports, require, module) {
/**
 * @module bodies/ball
 */

/**
 * Represents the ball
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} y - vertical position
 * @prop {number} dx - horizontal speed
 * @prop {number} dy - vertical speed
 */
exports.Ball = class Ball {
  constructor () {
    this.height = 0
    this.width = 0

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {object} bodyObj 
   */
  update ({ height, width, x, y }) {
    this.height = height
    this.width = width
    this.x = x
    this.y = y
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(this.color)
    s.rect(this.x, this.y, this.height, this.width, this.width)
  }
}

});

;require.register("src/bodies/brick.js", function(exports, require, module) {
/**
 * @module bodies/brick
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents a brick
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} height
 * @prop {number} width
 * @prop {number[]} color
 */
exports.Brick = class Brick {
  constructor(x, height, width) {
    this.x = x
    this.y = 0
    this.height = height
    this.width = width
    this.color = utils.randomColor()
  }

  /**
   * Move the ball to new position
   * @method
   * @param {int} dx
   * @param {int} dy
   */
  move(dx, dy) {
    this.x += dx
    this.y += dy
  }

  /**
   * Draw the brick on the screen
   * @method
   */
  draw() {
    fill.apply(fill, this.color)
    rect(this.x, this.y, this.width, this.height)
  }
}

/**
 * Represents a row of bricks
 * @class
 * @param {number} rowIndex - The index of row
 * @prop {Brick[]} bricks
 */
exports.BrickRow = class BrickRow {
  constructor(rowIndex){
    const count = 8
    const margin = 10
    const height = 30
    const width = (constants.C_WIDTH - count * margin) / count

    // Create new row
    this.bricks = new Array(count)
      .fill(null)
      .map((_, i) => new Brick((width + margin) * i, height, width))
  }

  /**
   * Move the bricks in this row down 1 row
   * @method
   */
  moveDown() {
    for (const brick of this.bricks) {
      brick.move(0, height + margin)
    }
  }

  /**
   * Checks if the ball colides with a brick in the row
   * @method
   * @param {Ball} ball
   * @return {Ball}
   */
  isBallCollision(ball) {
    for (const brick of this.bricks) {
      if (utils.isBallCollision(ball, brick)) {
        return brick
      }
    }
    return null
  }

  /**
   * Removes brick from the row
   * @method
   * @param {Brick} brick
   */
  removeBrick(brick) {
    this.bricks = this.bricks.filter((b) => b !== brick)
  }

  /**
   * Checks if the row is empty
   * @method
   * @return {bool}
   */
  isEmpty() {
    return this.bricks.length < 1
  }

  /**
   * Draws the bricks
   * @method
   */
  draw() {
    for (const brick of this.bricks) {
      brick.draw()
    }
  }
}

});

;require.register("src/bodies/index.js", function(exports, require, module) {
const { Ball } = require('./ball')
const { Paddle } = require('./paddle')
const { Brick } = require('./brick')

exports.bodiesMap = {
  Ball,
  Paddle,
  Brick
}

});

;require.register("src/bodies/paddle.js", function(exports, require, module) {
/**
 * @module bodies/paddle
 */

/**
 * Represents paddle
 * @class
 * @prop {number} height
 * @prop {number} width
 * @prop {number} borderRadius
 * @prop {number} x
 * @prop {number} y
 * @prop {string} color
 */
exports.Paddle = class Paddle {
  constructor () {
    this.height = 0
    this.width = 0
    this.borderRadius = 4

    this.x = 0
    this.y = 0

    this.color = 'white'
  }

  /**
   * Update body to match the server state
   * @param {object} bodyObj 
   */
  update ({ height, width, x, y }) {
    this.height = height
    this.width = width
    this.x = x
    this.y = y
  }

  /**
   * Daw the paddle on the screen
   * @method
   * @param {Sketch} s
   */
  draw (s) {
    s.fill(this.color)
    s.rect(this.x, this.y, this.width, this.height, this.borderRadius)
  }
}

});

;require.register("src/bodies/score.js", function(exports, require, module) {
/**
 * @module bodies/score
 */

const constants = require('../constants')

/**
 * Represents the user score
 * @class
 * @prop {number} score
 * @prop {string} color
 */
exports.Score = class Score {
  constructor (currentPlayer = false) {
    const gap = constants.C_HEIGHT * 0.20
    this.y = currentPlayer ? constants.C_HEIGHT - gap : gap
    this.points = 0
    this.color = 'white'
  }

  /**
   * @method
   */
  update ({ points }) {
    this.points = points
  }

  /**
   * Draws the score on the screen
   * @method
   */
  draw (s) {
    s.fill(this.color)
    s.textFont('Arial', 20)
    s.textAlign(s.CENTER)
    s.text(this.points, constants.C_WIDTH / 2, this.y)
  }
}

});

;require.register("src/constants.js", function(exports, require, module) {
/**
 * @module constants
 */

/**
 * API url
 * @type {string}
 */
exports.API_URL = ((window.location.protocol === 'https:') ? 'wss://' : 'ws://') + window.location.host + '/breakout/socket'

/**
 * Canvas height
 * @type {number}
 */
exports.C_HEIGHT = 450

/**
 * Canvas width
 * @type {number}
 */
exports.C_WIDTH = 300

/**
 * Is the current client the webview in the android app
 * @type {string}
 */
exports.IS_ANDROID_APP = navigator.userAgent.indexOf('Smash_It') > 1

});

;require.register("src/gameloop.js", function(exports, require, module) {
/**
 * @module gameLoop
 */

const { Player } = require('./player')
const { Ball } = require('./bodies/ball')
const { sketch } = require('./sketch')

/**
 * @param {string} str 
 */
const firstLetterToLowerCase = str => str[0].toLowerCase() + str.slice(1)

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle[]} paddles
 * @prop {Ball} ball
 */
class GameLoop {
  constructor () {
    this.reset()
  }

  reset () {
    // Initialise bodies
    this.players = []
    this.ball = new Ball()
  }

  updatePlayers (players) {
    if (this.players.length === 0){
       this.players = players.map(function (item, index){
           return new Player(index === 0, players.length === 2);
       })
    }
    
    for (let i = 0; i < this.players.length; i++) {
      this.players[i].update(players[i])
    }
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object[]} bodyObj 
   */
  updateBodies (bodies) {
    for (const bodyObj of bodies) {
      const instanceKey = firstLetterToLowerCase(bodyObj.type)
      this[instanceKey].update(bodyObj)
    }

    this.run()
  }

  /**
   * Draws the current state onto the provided sketch
   * @method
   */
  run () {
    // Clear canvas
    sketch.background(0)

    for (const player of this.players) {
      player.paddle.draw(sketch)
      if (player.score){
        player.score.draw(sketch)
      }
    }

    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()

});

;require.register("src/generate_levels.js", function(exports, require, module) {
$('.container_singleplayer_gamemode a img').on('click', function () {
  generate()
})

function generate () {
  const $selector = $(this)
  let $resultaatstring = ''
  let min = $selector.attr('data-min')
  let max = parseInt($selector.attr('data-max')) + 1
  for (max; min < max; min++) {
    $resultaatstring += '<a href="#" class="level" data-level="' + min + '"><span class="levelName">' + min + '</span></a>'
  }
  $('.generate_levels_singeplayer').html($resultaatstring)
}

});

;require.register("src/get_nation_data.js", function(exports, require, module) {
const URL_IP = 'http://www.geoplugin.net/json.gp?jsoncallback=?'
$.getJSON(URL_IP, function (data) {
  const nationsCode = JSON.stringify(data.geoplugin_countryCode, null, 2)
  console.log(nationsCode)
})

});

;require.register("src/initialize.js", function(exports, require, module) {
/**
 * @module initialize
 */

// const { wsClient } = require('./socket/client')
// const loadingView = require('./views/loading')
const constants = require('./constants')

// require('./sketch')

require('./music')
require('./qr_code')
require('./stats')
require('./get_nation_data')
require('./generate_levels')

const { viewManager } = require('./views/index')

viewManager.go('modes.html')

// window.wsClient = wsClient
// wsClient.open()

/**
 * - The android app needs 4,5 sec to show the vid.
 * - The body needs to be set to the full height of the browser (vh is not supported in webview)
 * - Fade in the body
 */
const timeout = constants.IS_ANDROID_APP ? 4500 : 0
setTimeout(function () {
  $('body').css('height', window.innerHeight)
  $('#start').addClass('load')
}, timeout)

/**
 * Prevent all hyperlinks from opening the web page manually and use viewManager
 */
$('body').on('click', 'a', function (e) {
  const href = e.currentTarget.getAttribute('href')
  if (!href || href === '#') {
    return
  }
  e.preventDefault()
  viewManager.go(href)
})

});

;require.register("src/music.js", function(exports, require, module) {
/**
 * Created by svend on 4/04/2017.
 */

/*********************************************PLAY_MUSIC********************************************/

var select  = function(){
    var val = $(this).attr('data-val');
    playMusic(val);
};

var playMusic = function(audioName){
    var snd = new Audio('music/' + audioName + '.mp3');
    snd.play();
};

/*********************************************INIT********************************************/
var init = function () {
    $('.click_me').on('click', select);
};
$(document).ready(init());
});

require.register("src/player.js", function(exports, require, module) {
/** @module player */

const { Paddle } = require('./bodies/paddle')
const { Score } = require('./bodies/score')

exports.Player = class Player {
  constructor (currentPlayer = false, multiplayer = true) {
    this.currentPlayer = currentPlayer
    this.paddle = new Paddle()
    this.multiplayer = multiplayer
    if (this.multiplayer){
        this.score = new Score(currentPlayer)
    }
  }

  update ({ paddle, score }) {
    this.paddle.update(paddle)
    if (this.multiplayer){
        this.score.update(score)
    }
  }
}

});

;require.register("src/qr_code.js", function(exports, require, module) {
$('#start_QR_scan').on('click', function () {
  SmashIt.startQRCode()
})

});

;require.register("src/sketch.js", function(exports, require, module) {
/**
 * @module sketch
 */

const P5 = require('p5')
const constants = require('./constants')

exports.sketch = new P5(function (sketch) {
  sketch.setup = function () {
    const canvas = sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)
    canvas.parent('game_started_view')

    // Don't loop on its own because we draw manualy when the server send and update
    sketch.noLoop()
  }
  sketch.draw = () => {}
})

});

;require.register("src/socket/client.js", function(exports, require, module) {
/**
 * @module socket/client
 */

const constants = require('../constants')
const connectionLossView = require('../views/connection_loss')
const initGameView = require('../views/init_game')
const { requestActionsMap } = require('../actions/index')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
 * @prop {String} clientId - UUID that the socket server gives to our client with the ConnectionSuccessAction
 */
class WsClient {
  /**
   * Open connection
   * @method
   */
  open () {
    if (this.ws !== undefined && this.ws.readyState !== WebSocket.CLOSED) {
      throw new Error('WebSocket is already opened.')
    }

    this.clientId = null

    this.ws = new WebSocket(constants.API_URL)

    this.ws.onopen = this.onOpen
    this.ws.onclose = this.onClose
    this.ws.onmessage = this.onMessage
  }

  /**
   * Set the clientId
   * Only done once
   * @method
   */
  setClientId (clientId) {
    this.cliendId = clientId
  }

  /**
   * Event handler for succesfull connection
   * @method
   */
  onOpen () {
    initGameView.show()
  }

  /**
   * Event handler for connection loss
   * @method
   */
  onClose () {
    connectionLossView.show()
    throw new Error('WebSocket was closed.')
  }

  /**
   * Event handler for receicing messages
   * @method
   */
  onMessage (event) {
    const action = JSON.parse(event.data)

    const RequestAction = requestActionsMap[action.type]
    if (RequestAction) {
      const a = new RequestAction(action)
      a.handler()
    }
  }

  /**
   * Send an action to the server
   * @method
   * @param {RequestAction} action
   */
  send (action) {
    if (!this.ws) {
      throw new Error("Websocket isn't yet open")
    }

    // Set action type as the name of the class
    action.type = action.constructor.name

    const json = JSON.stringify(action)
    this.ws.send(json)
  }
}

exports.wsClient = new WsClient()

});

;require.register("src/stats.js", function(exports, require, module) {
const nationsCode = [{
  player_won: {
    name: 'Jonas',
    countryCode: 'BE'
  },
  player_lost: {
    name: 'Fritz',
    countryCode: 'DE'
  },
  points: 250,
  time: 120
}, {
  player_won: {
    name: 'Thomas',
    countryCode: 'CH'
  },
  player_lost: {
    name: 'Neymar',
    countryCode: 'PT'
  },
  points: 500,
  time: 50
}, {
  player_won: {
    name: 'Ishan',
    countryCode: 'CN'
  },
  player_lost: {
    name: 'Max',
    countryCode: 'NL'
  },
  points: 600,
  time: 30
}, {
  player_won: {
    name: 'Sven',
    countryCode: 'EU'
  },
  player_lost: {
    name: 'LX',
    countryCode: 'SG'
  },
  points: 600,
  time: 30
}]
// colspan rowspan
function createTableStats () {
  let $resultaatString = '<table class="table"><thead><tr><th></th><th>Players</th><th>Points</th><th>Time</th></tr></thead>'
  $resultaatString += '<tbody>'

  $.each(nationsCode, function (index, item) {
    const wonPlayerCC = (item.player_won.countryCode).toLowerCase()
    const lostPlayerCC = (item.player_lost.countryCode).toLocaleLowerCase()
    $resultaatString += createRow(wonPlayerCC, item, item.points)
    $resultaatString += createRow(lostPlayerCC, item, 0)
  })
  $resultaatString += '</tbody></table>'
  return $resultaatString
}

function createRow (playerCC, item, points) {
  let tr = '<tr>'
  let ctd = '</td>'
  let ctr = '</td></tr>'
  let $resultaatString = ''
  $resultaatString += tr + '<td class="country">'
  $resultaatString += createImgString(playerCC)
  $resultaatString += ctd + '<td class="name">'
  if (points === 0) {
    $resultaatString += item.player_lost.name
  } else {
    $resultaatString += item.player_won.name
  }
  $resultaatString += ctd + '<td class="points">'
  $resultaatString += points
  if (points === 0) {} else {
    $resultaatString += ctd + '<td class="time">'
    $resultaatString += calcTime(item.time)
  }
  $resultaatString += ctr
  return $resultaatString
}

function timePadding (time, isBack) {
  const newtime = time.toString()
  if (newtime.length < 2) {
    return isBack ? newtime + '0' : '0' + newtime
  } else {
    return newtime
  }
}

function calcTime (timeInSec) {
  const min = Math.floor(timeInSec / 60)
  const sec = timeInSec - (min * 60)
  return timePadding(min) + ':' + timePadding(sec)
}

$('.stats_multiplayer').html(createTableStats())

function createImgString (nation) {
  return '<img src="images/nations/' + nation + '.png" alt="' + nation + '" title="' + nation + '"/>'
}

});

;require.register("src/utils.js", function(exports, require, module) {
/**
 * @module utils
 */

/**
 * Calculate distance between 2 points
 * @param {number} x1
 * @param {number} y1
 * @param {number} x2
 * @param {number} y2
 * @return {number}
 */
function calcPointsDistance (x1, y1, x2, y2) {
  let xDist = x2 - x1
  let yDist = y2 - y1

  // Pythagoras
  return Math.sqrt(Math.pow(xDist, 2) + Math.pow(yDist, 2))
}
exports.calcPointsDistance = calcPointsDistance

/**
 * Check if value is between min and max
 * @param {number} val
 * @param {number} min
 * @param {number} max
 * @return {boolean}
 */
function inRange (val, min, max) {
  return val >= Math.min(min, max) && val <= Math.max(min, max)
}
exports.inRange = inRange

/**
 * Check if there is a collision between ball and brick
 * @param {Ball} ball
 * @param {Brick} brick
 * @return {boolean}
 */
function isBallCollision (ball, brick) {
  return inRange(ball.x, brick.x - ball.radius, brick.x + brick.width + ball.radius) &&
    inRange(ball.y, brick.y - ball.radius, brick.y + brick.height + ball.radius)
}
exports.isBallCollision = isBallCollision

/**
 * Generate random number between min and max
 * @param {number} min
 * @param {number} max
 * @return {number}
 */
function randomInRange (min, max) {
  min = Math.ceil(min)
  max = Math.floor(max)
  return Math.round(Math.random() * (max - min)) + min
}
exports.randomInRange = randomInRange

/**
 * Generate either 1 or -1
 * @return {number}
 */
function randomSign () {
  return Math.round(Math.random()) ? 1 : -1
}
exports.randomSign = randomSign

/**
 * Generate random color in RGB
 * @return {number[]}
 */
function randomColor () {
  return [0, 0, 0].map(() => randomInRange(50, 255))
}
exports.randomColor = randomColor

});

;require.register("src/views/connection_loss.js", function(exports, require, module) {
/**
 * @module views/connection_loss
 */

const { showView } = require('../utils')

const els = {
  container: $('#connection_loss_view')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/create_multiplayer_screen.js", function(exports, require, module) {
const {
  CreateMultiplayerRequestAction
} = require('../actions/create_multiplayer_request')

const path = 'create.html'
exports.path = path

exports.CreateMultiplayerScreenView = class CreateMultiplayerScreenView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.createGameBtn = 'a.create_a_private_game'
  }

  createMultiplayerHandler () {
    window.wsClient.send(new CreateMultiplayerRequestAction())
  }

  onLoad () {
    $(this.createGameBtn).on('click', this.multiplayerClickHandler)
  }

  onUnload () {
    $(this.createGameBtn).off('click')
  }
}

});

;require.register("src/views/created_game.js", function(exports, require, module) {
const {
  CreateMultiplayerRequestAction
} = require('../actions/create_multiplayer_request')

const path = 'multiplayer.html'
exports.path = path

exports.MultiplayerScreenView = class MultiplayerScreenView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager

    this.createGameBtn = 'button.createGame'
  }

  createMultiplayerHandler () {
    window.wsClient.send(new CreateMultiplayerRequestAction())
  }

  onLoad () {
    $(this.createGameBtn).on('click', this.multiplayerClickHandler)
  }

  onUnload () {
    $(this.createGameBtn).off('click')
  }
}

});

;require.register("src/views/game_loss.js", function(exports, require, module) {
/**
 * @module views/game_loss
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_loss_view')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/game_started.js", function(exports, require, module) {
/**
 * @module views/game_started
 */

const { showView } = require('../utils')
const { MovePaddleStartAction } = require('../actions/move_paddle_start')
const { MovePaddleStopAction } = require('../actions/move_paddle_stop')

const els = {
  container: $('#game_started_view')
}

exports.show = function show () {
  showView(els.container)
  let keyCodePressed

  $(window).on('keydown', function (e) {
    if (keyCodePressed === e.keyCode) {
      return
    }
    keyCodePressed = e.keyCode

    switch (e.key) {
      case 'ArrowLeft':
        window.wsClient.send(new MovePaddleStartAction('left'))
        break
      case 'ArrowRight':
        window.wsClient.send(new MovePaddleStartAction('right'))
        break
    }
  })

  $(window).on('keyup', function (e) {
    keyCodePressed = undefined
    window.wsClient.send(new MovePaddleStopAction())
  })
}

let lastDirection

function getDirection ({ touches }) {
  console.log(touches)
  const xPos = touches[touches.length - 1].pageX
  return xPos > window.innerWidth / 2 ? 'right' : 'left'
}

function handleTouchStart (e) {
  e.preventDefault()
  const direction = getDirection(e)
  lastDirection = direction
  window.wsClient.send(new MovePaddleStartAction(direction))
  return false
}

function handleTouchEnd (e) {
  window.wsClient.send(new MovePaddleStopAction())
}

const listenerOptions = { passive: false }
els.container[0].addEventListener('touchstart', handleTouchStart, listenerOptions)
els.container[0].addEventListener('touchend', handleTouchEnd, listenerOptions)
els.container[0].addEventListener('touchcancel', handleTouchEnd, listenerOptions)

});

;require.register("src/views/game_stopped.js", function(exports, require, module) {
/**
 * @module views/game_stopped
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_stopped_view')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/game_victory.js", function(exports, require, module) {
/**
 * @module views/game_victory
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_victory_view')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/index.js", function(exports, require, module) {
const modeScreen = require('./mode_screen')
const multiplayerScreen = require('./multiplayer_screen')
const createMultiplayerScreen = require('./create_multiplayer_screen')

const viewsMap = {
  [modeScreen.path]: modeScreen.ModeScreenView,
  [multiplayerScreen.path]: multiplayerScreen.MultiplayerScreenView,
  [createMultiplayerScreen.path]: createMultiplayerScreen.CreateMultiplayerScreenView
}
exports.viewsMap = viewsMap

class ViewManager {
  constructor () {
    this.viewHistory = []
  }

  go (path) {
    const ViewConstructor = viewsMap[path]

    if (!ViewConstructor) {
      throw new Error(`View "${path}" doesn't exist.`)
    }

    const view = new ViewConstructor(this)

    $.ajax({
      url: path
    }).done(html => {
      const currentView = this.viewHistory[this.viewHistory.length - 1]
      if (currentView) {
        currentView.onUnload()
      }
      $('.screen').removeClass('currentScreen')
      $(document.body).append(`<div class="screen">${html}</div>`)

      setTimeout(function () {
        $('.screen')
          .last()
          .addClass('currentScreen')
        $('.screen:not(.currentScreen)').remove()
      }, 100)

      view.onLoad()

      this.viewHistory.push(view)
    })
  }
}

exports.viewManager = new ViewManager()

});

;require.register("src/views/init_game.js", function(exports, require, module) {
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

});

;require.register("src/views/mode_screen.js", function(exports, require, module) {
const path = 'modes.html'
exports.path = path

exports.ModeScreenView = class ModeScreenView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
  }
  onLoad () {
  }

  onUnload () {
  }
}

});

;require.register("src/views/multiplayer_screen.js", function(exports, require, module) {
const path = 'multiplayer.html'
exports.path = path

exports.MultiplayerScreenView = class MultiplayerScreenView {
  constructor (viewManager) {
    this.path = path
    this.viewManager = viewManager
  }

  onLoad () {
  }

  onUnload () {
  }
}

});

;require.alias("buffer/index.js", "buffer");
require.alias("process/browser.js", "process");process = require('process');require.register("___globals___", function(exports, require, module) {
  

// Auto-loaded modules from config.npm.globals.
window.jQuery = require("jquery");
window["$"] = require("jquery");


});})();require('___globals___');


//# sourceMappingURL=app.js.map