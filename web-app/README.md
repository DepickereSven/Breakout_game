# User instructions

## Setup

1. Install nodejs
- https://nodejs.org/en/download/

2. Install dependencies
- Open the nodejs shell on windows or terminal on mac and cd to the 2017_S3_Groep_11/web-app folder.
- Run `npm install`

## Development

1. Start local development server
```
npm start
```

2. Open localhost:3333 in browser

---

# API

## Modules

<dl>
<dt><a href="#module_constants">constants</a></dt>
<dd></dd>
<dt><a href="#module_initialize">initialize</a></dt>
<dd></dd>
<dt><a href="#module_utils">utils</a></dt>
<dd></dd>
<dt><a href="#module_bodies/ball">bodies/ball</a></dt>
<dd></dd>
<dt><a href="#module_bodies/brick">bodies/brick</a></dt>
<dd></dd>
<dt><a href="#module_bodies/paddle">bodies/paddle</a></dt>
<dd></dd>
<dt><a href="#module_bodies/score">bodies/score</a></dt>
<dd></dd>
</dl>

<a name="module_constants"></a>

## constants

* [constants](#module_constants)
    * [.C_HEIGHT](#module_constants.C_HEIGHT) : <code>number</code>
    * [.C_WIDTH](#module_constants.C_WIDTH) : <code>number</code>

<a name="module_constants.C_HEIGHT"></a>

### constants.C_HEIGHT : <code>number</code>
Canvas height

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.C_WIDTH"></a>

### constants.C_WIDTH : <code>number</code>
Canvas width

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_initialize"></a>

## initialize
<a name="module_utils"></a>

## utils

* [utils](#module_utils)
    * [~calcPointsDistance(x1, y1, x2, y2)](#module_utils..calcPointsDistance) ⇒ <code>number</code>
    * [~inRange(val, min, max)](#module_utils..inRange) ⇒ <code>boolean</code>
    * [~isBallCollision(ball, brick)](#module_utils..isBallCollision) ⇒ <code>boolean</code>
    * [~randomInRange(min, max)](#module_utils..randomInRange) ⇒ <code>number</code>
    * [~randomSign()](#module_utils..randomSign) ⇒ <code>number</code>
    * [~randomColor()](#module_utils..randomColor) ⇒ <code>Array.&lt;number&gt;</code>

<a name="module_utils..calcPointsDistance"></a>

### utils~calcPointsDistance(x1, y1, x2, y2) ⇒ <code>number</code>
Calculate distance between 2 points

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type |
| --- | --- |
| x1 | <code>number</code> | 
| y1 | <code>number</code> | 
| x2 | <code>number</code> | 
| y2 | <code>number</code> | 

<a name="module_utils..inRange"></a>

### utils~inRange(val, min, max) ⇒ <code>boolean</code>
Check if value is between min and max

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type |
| --- | --- |
| val | <code>number</code> | 
| min | <code>number</code> | 
| max | <code>number</code> | 

<a name="module_utils..isBallCollision"></a>

### utils~isBallCollision(ball, brick) ⇒ <code>boolean</code>
Check if there is a collision between ball and brick

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type |
| --- | --- |
| ball | <code>Ball</code> | 
| brick | <code>Brick</code> | 

<a name="module_utils..randomInRange"></a>

### utils~randomInRange(min, max) ⇒ <code>number</code>
Generate random number between min and max

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type |
| --- | --- |
| min | <code>number</code> | 
| max | <code>number</code> | 

<a name="module_utils..randomSign"></a>

### utils~randomSign() ⇒ <code>number</code>
Generate either 1 or -1

**Kind**: inner method of [<code>utils</code>](#module_utils)  
<a name="module_utils..randomColor"></a>

### utils~randomColor() ⇒ <code>Array.&lt;number&gt;</code>
Generate random color in RGB

**Kind**: inner method of [<code>utils</code>](#module_utils)  
<a name="module_bodies/ball"></a>

## bodies/ball

* [bodies/ball](#module_bodies/ball)
    * [.Ball](#module_bodies/ball.Ball)
        * [new exports.Ball()](#new_module_bodies/ball.Ball_new)
        * [.move(dx, dy)](#module_bodies/ball.Ball+move)
        * [.draw()](#module_bodies/ball.Ball+draw)

<a name="module_bodies/ball.Ball"></a>

### bodies/ball.Ball
**Kind**: static class of [<code>bodies/ball</code>](#module_bodies/ball)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| x | <code>int</code> | horizontal position |
| y | <code>int</code> | vertical position |
| dx | <code>int</code> | horizontal speed |
| dy | <code>int</code> | vertical speed |


* [.Ball](#module_bodies/ball.Ball)
    * [new exports.Ball()](#new_module_bodies/ball.Ball_new)
    * [.move(dx, dy)](#module_bodies/ball.Ball+move)
    * [.draw()](#module_bodies/ball.Ball+draw)

<a name="new_module_bodies/ball.Ball_new"></a>

#### new exports.Ball()
Represents the ball

<a name="module_bodies/ball.Ball+move"></a>

#### ball.move(dx, dy)
Move the ball to new position

**Kind**: instance method of [<code>Ball</code>](#module_bodies/ball.Ball)  

| Param | Type |
| --- | --- |
| dx | <code>int</code> | 
| dy | <code>int</code> | 

<a name="module_bodies/ball.Ball+draw"></a>

#### ball.draw()
Draw the ball on the provides 2D context

**Kind**: instance method of [<code>Ball</code>](#module_bodies/ball.Ball)  
<a name="module_bodies/brick"></a>

## bodies/brick

* [bodies/brick](#module_bodies/brick)
    * [~Brick](#module_bodies/brick..Brick)
        * [new Brick()](#new_module_bodies/brick..Brick_new)
        * [.move(dx, dy)](#module_bodies/brick..Brick+move)
        * [.draw()](#module_bodies/brick..Brick+draw)
    * [~BrickRow](#module_bodies/brick..BrickRow)
        * [new BrickRow(rowIndex)](#new_module_bodies/brick..BrickRow_new)
        * [.moveDown()](#module_bodies/brick..BrickRow+moveDown)
        * [.isBallCollision(ball)](#module_bodies/brick..BrickRow+isBallCollision) ⇒ <code>Ball</code>
        * [.removeBrick(brick)](#module_bodies/brick..BrickRow+removeBrick)
        * [.isEmpty()](#module_bodies/brick..BrickRow+isEmpty) ⇒ <code>bool</code>
        * [.draw()](#module_bodies/brick..BrickRow+draw)

<a name="module_bodies/brick..Brick"></a>

### bodies/brick~Brick
**Kind**: inner class of [<code>bodies/brick</code>](#module_bodies/brick)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| x | <code>number</code> | horizontal position |
| height | <code>number</code> |  |
| width | <code>number</code> |  |
| color | <code>Array.&lt;number&gt;</code> |  |


* [~Brick](#module_bodies/brick..Brick)
    * [new Brick()](#new_module_bodies/brick..Brick_new)
    * [.move(dx, dy)](#module_bodies/brick..Brick+move)
    * [.draw()](#module_bodies/brick..Brick+draw)

<a name="new_module_bodies/brick..Brick_new"></a>

#### new Brick()
Represents a brick

<a name="module_bodies/brick..Brick+move"></a>

#### brick.move(dx, dy)
Move the ball to new position

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick..Brick)  

| Param | Type |
| --- | --- |
| dx | <code>int</code> | 
| dy | <code>int</code> | 

<a name="module_bodies/brick..Brick+draw"></a>

#### brick.draw()
Draw the brick on the screen

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick..Brick)  
<a name="module_bodies/brick..BrickRow"></a>

### bodies/brick~BrickRow
**Kind**: inner class of [<code>bodies/brick</code>](#module_bodies/brick)  
**Properties**

| Name | Type |
| --- | --- |
| bricks | <code>Array.&lt;Brick&gt;</code> | 


* [~BrickRow](#module_bodies/brick..BrickRow)
    * [new BrickRow(rowIndex)](#new_module_bodies/brick..BrickRow_new)
    * [.moveDown()](#module_bodies/brick..BrickRow+moveDown)
    * [.isBallCollision(ball)](#module_bodies/brick..BrickRow+isBallCollision) ⇒ <code>Ball</code>
    * [.removeBrick(brick)](#module_bodies/brick..BrickRow+removeBrick)
    * [.isEmpty()](#module_bodies/brick..BrickRow+isEmpty) ⇒ <code>bool</code>
    * [.draw()](#module_bodies/brick..BrickRow+draw)

<a name="new_module_bodies/brick..BrickRow_new"></a>

#### new BrickRow(rowIndex)
Represents a row of bricks


| Param | Type | Default | Description |
| --- | --- | --- | --- |
| rowIndex | <code>number</code> | <code>0</code> | The index of row |

<a name="module_bodies/brick..BrickRow+moveDown"></a>

#### brickRow.moveDown()
Move the bricks in this row down 1 row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick..BrickRow)  
<a name="module_bodies/brick..BrickRow+isBallCollision"></a>

#### brickRow.isBallCollision(ball) ⇒ <code>Ball</code>
Checks if the ball colides with a brick in the row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick..BrickRow)  

| Param | Type |
| --- | --- |
| ball | <code>Ball</code> | 

<a name="module_bodies/brick..BrickRow+removeBrick"></a>

#### brickRow.removeBrick(brick)
Removes brick from the row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick..BrickRow)  

| Param | Type |
| --- | --- |
| brick | <code>Brick</code> | 

<a name="module_bodies/brick..BrickRow+isEmpty"></a>

#### brickRow.isEmpty() ⇒ <code>bool</code>
Checks if the row is empty

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick..BrickRow)  
<a name="module_bodies/brick..BrickRow+draw"></a>

#### brickRow.draw()
Draws the bricks

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick..BrickRow)  
<a name="module_bodies/paddle"></a>

## bodies/paddle

* [bodies/paddle](#module_bodies/paddle)
    * [.Paddle](#module_bodies/paddle.Paddle)
        * [new exports.Paddle()](#new_module_bodies/paddle.Paddle_new)
        * [.move(dx)](#module_bodies/paddle.Paddle+move)
        * [.draw()](#module_bodies/paddle.Paddle+draw)

<a name="module_bodies/paddle.Paddle"></a>

### bodies/paddle.Paddle
**Kind**: static class of [<code>bodies/paddle</code>](#module_bodies/paddle)  
**Properties**

| Name | Type |
| --- | --- |
| height | <code>number</code> | 
| width | <code>number</code> | 
| borderRadius | <code>number</code> | 
| x | <code>number</code> | 
| y | <code>number</code> | 
| color | <code>string</code> | 


* [.Paddle](#module_bodies/paddle.Paddle)
    * [new exports.Paddle()](#new_module_bodies/paddle.Paddle_new)
    * [.move(dx)](#module_bodies/paddle.Paddle+move)
    * [.draw()](#module_bodies/paddle.Paddle+draw)

<a name="new_module_bodies/paddle.Paddle_new"></a>

#### new exports.Paddle()
Represents paddle

<a name="module_bodies/paddle.Paddle+move"></a>

#### paddle.move(dx)
Move the paddle horizontally

**Kind**: instance method of [<code>Paddle</code>](#module_bodies/paddle.Paddle)  

| Param | Type | Description |
| --- | --- | --- |
| dx | <code>number</code> | Relative change in x |

<a name="module_bodies/paddle.Paddle+draw"></a>

#### paddle.draw()
Daw the paddle on the screen

**Kind**: instance method of [<code>Paddle</code>](#module_bodies/paddle.Paddle)  
<a name="module_bodies/score"></a>

## bodies/score

* [bodies/score](#module_bodies/score)
    * [.Score](#module_bodies/score.Score)
        * [new exports.Score()](#new_module_bodies/score.Score_new)
        * [.add()](#module_bodies/score.Score+add)
        * [.get()](#module_bodies/score.Score+get) ⇒ <code>number</code>
        * [.draw()](#module_bodies/score.Score+draw)

<a name="module_bodies/score.Score"></a>

### bodies/score.Score
**Kind**: static class of [<code>bodies/score</code>](#module_bodies/score)  
**Properties**

| Name | Type |
| --- | --- |
| score | <code>number</code> | 
| color | <code>string</code> | 


* [.Score](#module_bodies/score.Score)
    * [new exports.Score()](#new_module_bodies/score.Score_new)
    * [.add()](#module_bodies/score.Score+add)
    * [.get()](#module_bodies/score.Score+get) ⇒ <code>number</code>
    * [.draw()](#module_bodies/score.Score+draw)

<a name="new_module_bodies/score.Score_new"></a>

#### new exports.Score()
Represents the user score

<a name="module_bodies/score.Score+add"></a>

#### score.add()
Increases the score by 1

**Kind**: instance method of [<code>Score</code>](#module_bodies/score.Score)  
<a name="module_bodies/score.Score+get"></a>

#### score.get() ⇒ <code>number</code>
Get the current score

**Kind**: instance method of [<code>Score</code>](#module_bodies/score.Score)  
<a name="module_bodies/score.Score+draw"></a>

#### score.draw()
Draws the score on the screen

**Kind**: instance method of [<code>Score</code>](#module_bodies/score.Score)  
