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
require.register("src/actions/create_game_request.js", function(exports, require, module) {
/**
 * @module actions/create_game_request.js
 */

exports.CreateGameRequestAction = class CreateGameRequestAction {}

});

;require.register("src/actions/create_game_success.js", function(exports, require, module) {
/**
 * @module actions/create_game_success.js
 */

const createdGameView = require('../views/created_game')

exports.CreateGameSuccessAction = class CreateGameSuccessAction {
  constructor ({ key }) {
    this.key = key
  }

  handler () {
    createdGameView.show(this.key)
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
  constructor ({ bodies }) {
    this.bodies = bodies
  }

  handler () {
    gameLoop.update(this.bodies)
  }
}

});

;require.register("src/actions/index.js", function(exports, require, module) {
const { CreateGameSuccessAction } = require('./create_game_success')
const { GameStartAction } = require('./game_start')
const { GameStateUpdateAction } = require('./game_state_update')

exports.requestActionsMap = {
  CreateGameSuccessAction,
  GameStartAction,
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

;require.register("src/actions/move_paddle_left.js", function(exports, require, module) {
/**
 * @module actions/move_paddle_left.js
 */

exports.MovePaddleLeftAction = class MovePaddleLeftAction {}

});

;require.register("src/actions/move_paddle_right.js", function(exports, require, module) {
/**
 * @module actions/move_paddle_right.js
 */

exports.MovePaddleRightAction = class MovePaddleRightAction {}

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
const utils = require('../utils')

/**
 * Represents the user score
 * @class
 * @prop {number} score
 * @prop {string} color
 */
exports.Score = class Score {
  constructor(){
    this.score = 0
    this.color = 'white'
  }

  /**
   * Increases the score by 1
   * @method
   */
  add() {
    this.score += 1
  }

  /**
   * Get the current score
   * @method
   * @return {number}
   */
  get() {
    return this.score
  }

  /**
   * Draws the score on the screen
   * @method
   */
  draw() {
    fill(color)
    textFont('Arial', 30)
    text(score, constants.C_WIDTH / 2, constants.C_HEIGHT / 2)
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
exports.API_URL = 'ws://localhost:8080/breakout/socket'


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

});

;require.register("src/gameloop.js", function(exports, require, module) {
/**
 * @module gameLoop
 */

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')
const { sketch } = require('./sketch')
const { MovePaddleLeftAction } = require('./actions/move_paddle_left')
const { MovePaddleRightAction } = require('./actions/move_paddle_right')

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
    this.paddles = [new Paddle(), new Paddle()]
    this.ball = new Ball()
  }

  /**
   * Update the body to match the server state
   * @method
   * @param {object[]} bodyObj 
   */
  update (bodies) {
    let paddleIndex = 0
    for (const bodyObj of bodies) {
      const instanceKey = firstLetterToLowerCase(bodyObj.type)
      if (instanceKey === 'paddle') {
        this.paddles[paddleIndex].update(bodyObj)
        paddleIndex++
      } else if (this[instanceKey]) {
        this[instanceKey].update(bodyObj)
      }
    }

    if (sketch.keyIsPressed) {
      switch (sketch.keyCode) {
        case 37:
          window.wsClient.send(new MovePaddleLeftAction())
          break
        case 39:
          window.wsClient.send(new MovePaddleRightAction())
          break
      }
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

    for (const paddle of this.paddles) {
      paddle.draw(sketch)
    }
    this.ball.draw(sketch)
  }
}

exports.gameLoop = new GameLoop()

});

;require.register("src/initialize.js", function(exports, require, module) {
/**
 * @module initialize
 */

const { wsClient } = require('./socket/client')
const loadingView = require('./views/loading')

require('./sketch')

$(document).ready(function () {
  loadingView.show()

  window.wsClient = wsClient
  wsClient.open()
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
    canvas.parent('game_started')

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

    this.ws = new WebSocket(constants.API_URL)

    this.ws.onopen = this.onOpen
    this.ws.onclose = this.onClose
    this.ws.onmessage = this.onMessage
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
function calcPointsDistance(x1, y1, x2, y2) {
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
function inRange(val, min, max) {
  return val >= Math.min(min, max) && val <= Math.max(min, max)
}
exports.inRange = inRange

/**
 * Check if there is a collision between ball and brick
 * @param {Ball} ball
 * @param {Brick} brick
 * @return {boolean}
 */
function isBallCollision(ball, brick) {
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
function randomInRange(min, max) {
  min = Math.ceil(min)
  max = Math.floor(max)
  return Math.round(Math.random() * (max - min)) + min
}
exports.randomInRange = randomInRange

/**
 * Generate either 1 or -1
 * @return {number}
 */
function randomSign() {
  return Math.round(Math.random()) ? 1 : -1
}
exports.randomSign = randomSign

/**
 * Generate random color in RGB
 * @return {number[]}
 */
function randomColor() {
  return [0, 0, 0].map(() => randomInRange(50, 255))
}
exports.randomColor = randomColor

/**
 * Show the given view and hide the others
 * @param {jQuery} el - jQuery container element
 */
function showView(el) {
  $('.view').addClass('hidden')
  el.removeClass('hidden')
}
exports.showView = showView

});

;require.register("src/views/connection_loss.js", function(exports, require, module) {
/**
 * @module views/connection_loss
 */

const { showView } = require('../utils')

const els = {
  container: $('#connection_loss')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/created_game.js", function(exports, require, module) {

/**
 * @module views/created_game
 */

const { showView } = require('../utils')

const els = exports.els = {
  container: $('#created_game_success'),
  createdGameKey: $('#created_game_key')
}

/**
 * Show the created game view
 * @param {string} key - Game session key
 */
exports.show = function show (key) {
  els.createdGameKey.text(key)
  showView(els.container)
}

});

;require.register("src/views/game_started.js", function(exports, require, module) {
/**
 * @module views/game_started
 */

const { showView } = require('../utils')

const els = {
  container: $('#game_started')
}

exports.show = function show () {
  showView(els.container)
}

});

;require.register("src/views/init_game.js", function(exports, require, module) {
/**
 * @module views/init_game
 */

const { showView } = require('../utils')
const { CreateGameRequestAction } = require('../actions/create_game_request')
const { JoinGameRequestAction } = require('../actions/join_game_request')

const els = {
  container: $('#init_game'),
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

});

;require.register("src/views/loading.js", function(exports, require, module) {
/**
 * @module views/loading
 */

const { showView } = require('../utils')

const els = {
  container: $('#loading')
}

exports.show = function show () {
  showView(els.container)
}
});

;require.alias("buffer/index.js", "buffer");
require.alias("process/browser.js", "process");process = require('process');require.register("___globals___", function(exports, require, module) {
  

// Auto-loaded modules from config.npm.globals.
window.jQuery = require("jquery");
window["$"] = require("jquery");


});})();require('___globals___');


//# sourceMappingURL=app.js.map