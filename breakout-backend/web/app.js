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
require.register("src/actions.js", function(exports, require, module) {
/**
 * Contains action creators
 * @module actions
 */

/**
 * Action object creator
 * @param {string} shortType
 * @param {object} payload
 */
function createAction (shortType, payload={}){
  const fullType = shortType + 'Action'
  return { type: fullType, ...payload }
}


exports.createGameRequest = () =>
  createAction('CreateGameRequest')

exports.joinGameRequest = (key) =>
  createAction('JoinGameRequest', { key })

});

;require.register("src/bodies/ball.js", function(exports, require, module) {
/**
 * @module bodies/ball
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents the ball
 * @class
 * @prop {number} x - horizontal position
 * @prop {number} y - vertical position
 * @prop {number} dx - horizontal speed
 * @prop {number} dy - vertical speed
 */
function Ball () {
  this.height = 0
  this.width = 0

  this.x = 0
  this.y = 0

  this.color = 'white'
}
exports.Ball = Ball


/**
 * Draw the ball on the provides 2D context
 * @method
 * @param {Sketch} s
 */
Ball.prototype.draw = function (s) {
  s.fill(this.color)
  s.ellipse(this.x, this.y, this.height)
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
 * @constructor
 * @prop {number} x - horizontal position
 * @prop {number} height
 * @prop {number} width
 * @prop {number[]} color
 */
function Brick (x, height, width) {
  this.x = x
  this.y = 0
  this.height = height
  this.width = width
  this.color = utils.randomColor()

  /**
   * Move the ball to new position
   * @method
   * @param {int} dx
   * @param {int} dy
   */
  this.move = function (dx, dy) {
    this.x += dx
    this.y += dy
  }

  /**
   * Draw the brick on the screen
   * @method
   */
  this.draw = function () {
    fill.apply(fill, this.color)
    rect(this.x, this.y, this.width, this.height)
  }
}
exports.Brick = Brick

/**
 * Represents a row of bricks
 * @constructor
 * @param {number} rowIndex - The index of row
 * @prop {Brick[]} bricks
 */
function BrickRow (rowIndex = 0) {
  const count = 8
  const margin = 10
  const height = 30
  const width = (constants.C_WIDTH - count * margin) / count

  // Create new row
  this.bricks = new Array(count)
    .fill(null)
    .map((_, i) => new Brick((width + margin) * i, height, width))

  /**
   * Move the bricks in this row down 1 row
   * @method
   */
  this.moveDown = function () {
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
  this.isBallCollision = function (ball) {
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
  this.removeBrick = function (brick) {
    this.bricks = this.bricks.filter((b) => b !== brick)
  }

  /**
   * Checks if the row is empty
   * @method
   * @return {bool}
   */
  this.isEmpty = function () {
    return this.bricks.length < 1
  }

  /**
   * Draws the bricks
   * @method
   */
  this.draw = function () {
    for (const brick of this.bricks) {
      brick.draw()
    }
  }
}

exports.BrickRow = BrickRow

});

;require.register("src/bodies/paddle.js", function(exports, require, module) {
/**
 * @module bodies/paddle
 */

const constants = require('../constants')
const utils = require('../utils')

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
function Paddle () {
  this.height = 0
  this.width = 0
  this.borderRadius = 4

  this.x = 0
  this.y = 0

  this.color = 'white'
}
exports.Paddle = Paddle


/**
 * Daw the paddle on the screen
 * @method
 * @param {Sketch} s
 */
Paddle.prototype.draw = function (s) {
  s.fill(this.color)
  s.rect(this.x, this.y, this.width, this.height, this.borderRadius)
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
exports.Score = function Score () {
  let score = 0
  const color = 'white'

  /**
   * Increases the score by 1
   * @method
   */
  this.add = function () {
    score += 1
  }

  /**
   * Get the current score
   * @method
   * @return {number}
   */
  this.get = function () {
    return score
  }

  /**
   * Draws the score on the screen
   * @method
   */
  this.draw = function () {
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

const constants = require('./constants')
const utils = require('./utils')

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')

/**
 * GameLoop provides the state and drawing for the sketch
 * @class
 * @prop {Paddle} paddle
 * @prop {Ball} ball
 */
function GameLoop() {
  // Initialise bodies
  this.paddle = new Paddle()
  this.ball = new Ball()
}
exports.GameLoop = GameLoop


/**
 * Draws the current state onto the provided sketch
 * @method
 * @param {Sketch} s - p5.js sketch object to draw on
 */
GameLoop.prototype.run = function run(s) {
  // Clear canvas
  s.background(0)
  s.fill(255)

  // Paddle controls
  {
    this.paddle.draw(s)
  }

  // Ball controls
  {
    this.ball.draw(s)
  }

}

});

;require.register("src/initialize.js", function(exports, require, module) {
/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const { GameLoop } = require('./gameloop')
const { wsClient } = require('./socket/client')

require('./userinput')

const state = {}

const p5 = new P5(function (sketch) {
  let gameLoop

  sketch.setup = function () {
    sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)

    wsClient.open()

    gameLoop  = new GameLoop()
  }

  sketch.draw = function () {
    gameLoop.run(sketch)
  }
})



});

;require.register("src/socket/client.js", function(exports, require, module) {
/**
 * @module socket/client
 */

const msgpack = require('msgpack-lite');

const constants = require('../constants')

/**
 * Websocket client
 * @class
 * @prop {WebSocket} ws
 */
function WsClient(){
}


/**
 * Open connection
 * @method
 */
WsClient.prototype.open = function open() {
  if(this.ws !== undefined && this.ws.readyState !== WebSocket.CLOSED){
    throw new Error('WebSocket is already opened.')
  }

  this.ws = new WebSocket(constants.API_URL)
  this.ws.binaryType = 'arraybuffer'

  this.ws.onopen = this.onOpen
  this.ws.onclose = this.onClose
  this.ws.onmessage = this.onMessage
}

/**
 * Event handler for succesfull connection
 * @method
 */
WsClient.prototype.onOpen = function onOpen() {
}

/**
 * Event handler for connection termination
 * @method
 */
WsClient.prototype.onClose = function onClose() {
  throw new Error('WebSocket was closed.')
}

/**
 * Event handler for receicing messages
 * @method
 */
WsClient.prototype.onMessage = function onMessage(event) {
  const bufferView = new Uint8Array(event.data)
  const action = msgpack.decode(bufferView)
  console.log(action)
}

/**
 * Send an action to the server
 * @method
 * @param {Action} action
 */
WsClient.prototype.send = function send(action) {
  if(!this.ws){
    throw new Error('Websocket isn\'t yet open')
  }
  console.log(action)
  const buffer = msgpack.encode(action)
  this.ws.send(buffer)
}


// Export a single WsClient instance
if(!window.wsClient){
  window.wsClient = new WsClient()
}

exports.wsClient = window.wsClient

});

;require.register("src/userinput.js", function(exports, require, module) {
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

});

;require.register("src/views/createdgame.js", function(exports, require, module) {

});

;require.register("src/views/initgame.js", function(exports, require, module) {

});

;require.alias("buffer/index.js", "buffer");
require.alias("process/browser.js", "process");process = require('process');require.register("___globals___", function(exports, require, module) {
  

// Auto-loaded modules from config.npm.globals.
window.jQuery = require("jquery");
window["$"] = require("jquery");


});})();require('___globals___');


//# sourceMappingURL=app.js.map