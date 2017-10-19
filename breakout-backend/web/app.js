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
require.register("src/bodies/ball.js", function(exports, require, module) {
/**
 * @module bodies/ball
 */

const constants = require('../constants')
const utils = require('../utils')

/**
 * Represents the ball
 * @constructor
 * @prop {int} x - horizontal position
 * @prop {int} y - vertical position
 * @prop {int} dx - horizontal speed
 * @prop {int} dy - vertical speed
 */
exports.Ball = function Ball () {
  this.x = constants.C_WIDTH / 2
  this.y = constants.C_HEIGHT - 80

  this.dx = utils.randomInRange(4, 6) * utils.randomSign()
  this.dy = -8

  this.radius = 10
  this.color = 'white'

  /**
   * Move the ball to new position
   * @method
   * @param {int} dx
   * @param {int} dy
   */
  this.move = function (dx, dy) {
    this.dx = dx
    this.dy = dy

    this.x += dx
    this.y += dy
  }

  /**
   * Draw the ball on the provides 2D context
   * @method
   */
  this.draw = function () {
    fill(this.color)
    ellipse(this.x, this.y, this.radius * 2)
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
exports.Paddle = function Paddle () {
  this.height = 30
  this.width = 120
  this.borderRadius = 4

  this.x = (constants.C_WIDTH - this.width) / 2
  this.y = constants.C_HEIGHT - this.height - 10

  this.color = 'white'

  /**
   * Move the paddle horizontally
   * @param {number} dx - Relative change in x
   */
  this.move = function (dx) {
    this.x += dx
  }

  /**
   * Daw the paddle on the screen
   */
  this.draw = function () {
    fill(this.color)
    rect(this.x, this.y, this.width, this.height, this.borderRadius)
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
 * Canvas height
 * @type {number}
 */
exports.C_HEIGHT = 600


/**
 * Canvas width
 * @type {number}
 */
exports.C_WIDTH = 800

});

;require.register("src/initialize.js", function(exports, require, module) {
/**
 * @module initialize
 */

const P5 = require('p5')

const constants = require('./constants')
const utils = require('./utils')

const { Ball } = require('./bodies/ball')
const { Paddle } = require('./bodies/paddle')
const { BrickRow } = require('./bodies/brick')
const { Score } = require('./bodies/score')

const state = {}

function addBrickRow () {
  // Move other rows down
  for (const row of state.brickRows) {
    row.moveDown()
  }

  // Create new row
  const rowIndex = state.brickRows.length
  const row = new BrickRow(rowIndex)

  // Place in on top
  state.brickRows.unshift(row)
}

function removeBrickFromRow (brickRow, brick) {
  brickRow.removeBrick(brick)

  // Check if bottom row is empty
  const lastIndex = state.brickRows.length - 1
  const isBottomRow = brickRow == state.brickRows[lastIndex]

  if (isBottomRow && brickRow.isEmpty()) {
    state.brickRows.pop()
  }
}

const p5 = new P5(function (sketch) {

  // Set globals
  window.fill = sketch.fill.bind(sketch)
  window.background = sketch.background.bind(sketch)
  window.rect = sketch.rect.bind(sketch)
  window.ellipse = sketch.ellipse.bind(sketch)
  window.textFont = sketch.textFont.bind(sketch)
  window.text = sketch.text.bind(sketch)

  sketch.setup = function () {
    sketch.createCanvas(constants.C_WIDTH, constants.C_HEIGHT)

    // Initialise paddle
    state.paddle = new Paddle()
    state.paddleCollisions = 0

    // Initialise ball
    state.ball = new Ball()

    // Initialise bricks
    state.brickRows = []
    addBrickRow()
    addBrickRow()

    // Initialise score
    state.score = new Score()
  }

  sketch.draw = function () {
    // Clear canvas
    background(0)
    fill(255)

    // Paddle movement
    if (state.paddle) {
      const { x, y, width } = state.paddle

      if (sketch.keyIsPressed && sketch.keyCode == sketch.LEFT_ARROW && x > 0) {
        state.paddle.move(-8)
      }
      if (sketch.keyIsPressed && sketch.keyCode == sketch.RIGHT_ARROW && x < constants.C_WIDTH - width) {
        state.paddle.move(8)
      }

      state.paddle.draw()
    }

    // Ball movement
    if (state.ball) {
      let { x, y, dx, dy, radius } = state.ball

      // Wall collission
      if (x + dx > constants.C_WIDTH - radius || x + dx < radius) {
        dx = -dx

        // Ceiling collission
      } else if (y + dy < radius) {
        dy = -dy

        // Floor collission
      } else if (y + dy > constants.C_HEIGHT - radius) {
        console.log('GAME OVER')
        setup()
        return

        // Paddle collision
      } else if (utils.isBallCollision(state.ball, state.paddle)) {
        dy = -dy
        state.paddleCollisions += 1
        if (state.paddleCollisions > 4) {
          addBrickRow()
          state.paddleCollisions = 0
        }

        // Brick collision
      } else {
        // Reverse loop over the brickRows -> bottom rows will be first checked
        for (let i = state.brickRows.length - 1; i >= 0; i--) {
          const brickRow = state.brickRows[i]
          const brick = brickRow.isBallCollision(state.ball)

          if (brick) {
            removeBrickFromRow(brickRow, brick)

            state.score.add()
            dy = -dy
            break
          }
        }
      }

      state.ball.move(dx, dy)
      state.ball.draw()
    }

    for (const brickRow of state.brickRows) {
      brickRow.draw()
    }

    state.score.draw()
  }
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

;require.alias("buffer/index.js", "buffer");
require.alias("process/browser.js", "process");process = require('process');require.register("___globals___", function(exports, require, module) {
  

// Auto-loaded modules from config.npm.globals.
window.jQuery = require("jquery");
window["$"] = require("jquery");


});})();require('___globals___');

