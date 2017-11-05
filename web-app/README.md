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
<dt><a href="#module_gameLoop">gameLoop</a></dt>
<dd></dd>
<dt><a href="#module_initialize">initialize</a></dt>
<dd></dd>
<dt><a href="#module_sketch">sketch</a></dt>
<dd></dd>
<dt><a href="#module_utils">utils</a></dt>
<dd></dd>
<dt><a href="#actions/create_game_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/create_game_success.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_start.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_state_update.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_stop.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/join_game_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/move_paddle_left.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/move_paddle_right.module_js">js</a></dt>
<dd></dd>
<dt><a href="#module_bodies/ball">bodies/ball</a></dt>
<dd></dd>
<dt><a href="#module_bodies/brick">bodies/brick</a></dt>
<dd></dd>
<dt><a href="#module_bodies/paddle">bodies/paddle</a></dt>
<dd></dd>
<dt><a href="#module_bodies/score">bodies/score</a></dt>
<dd></dd>
<dt><a href="#module_socket/client">socket/client</a></dt>
<dd></dd>
<dt><a href="#module_views/connection_loss">views/connection_loss</a></dt>
<dd></dd>
<dt><a href="#module_views/created_game">views/created_game</a></dt>
<dd></dd>
<dt><a href="#module_views/game_started">views/game_started</a></dt>
<dd></dd>
<dt><a href="#module_views/game_stopped">views/game_stopped</a></dt>
<dd></dd>
<dt><a href="#module_views/init_game">views/init_game</a></dt>
<dd></dd>
<dt><a href="#module_views/loading">views/loading</a></dt>
<dd></dd>
</dl>

<a name="module_constants"></a>

## constants

* [constants](#module_constants)
    * [.API_URL](#module_constants.API_URL) : <code>string</code>
    * [.C_HEIGHT](#module_constants.C_HEIGHT) : <code>number</code>
    * [.C_WIDTH](#module_constants.C_WIDTH) : <code>number</code>

<a name="module_constants.API_URL"></a>

### constants.API_URL : <code>string</code>
API url

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.C_HEIGHT"></a>

### constants.C_HEIGHT : <code>number</code>
Canvas height

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.C_WIDTH"></a>

### constants.C_WIDTH : <code>number</code>
Canvas width

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_gameLoop"></a>

## gameLoop

* [gameLoop](#module_gameLoop)
    * [~GameLoop](#module_gameLoop..GameLoop)
        * [.update(bodyObj)](#module_gameLoop..GameLoop+update)
        * [.run()](#module_gameLoop..GameLoop+run)
    * [~firstLetterToLowerCase(str)](#module_gameLoop..firstLetterToLowerCase)

<a name="module_gameLoop..GameLoop"></a>

### gameLoop~GameLoop
GameLoop provides the state and drawing for the sketch

**Kind**: inner class of [<code>gameLoop</code>](#module_gameLoop)  
**Properties**

| Name | Type |
| --- | --- |
| paddles | <code>Array.&lt;Paddle&gt;</code> | 
| ball | <code>Ball</code> | 


* [~GameLoop](#module_gameLoop..GameLoop)
    * [.update(bodyObj)](#module_gameLoop..GameLoop+update)
    * [.run()](#module_gameLoop..GameLoop+run)

<a name="module_gameLoop..GameLoop+update"></a>

#### gameLoop.update(bodyObj)
Update the body to match the server state

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop..GameLoop)  

| Param | Type |
| --- | --- |
| bodyObj | <code>Array.&lt;object&gt;</code> | 

<a name="module_gameLoop..GameLoop+run"></a>

#### gameLoop.run()
Draws the current state onto the provided sketch

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop..GameLoop)  
<a name="module_gameLoop..firstLetterToLowerCase"></a>

### gameLoop~firstLetterToLowerCase(str)
**Kind**: inner method of [<code>gameLoop</code>](#module_gameLoop)  

| Param | Type |
| --- | --- |
| str | <code>string</code> | 

<a name="module_initialize"></a>

## initialize
<a name="module_sketch"></a>

## sketch
<a name="module_utils"></a>

## utils

* [utils](#module_utils)
    * [~calcPointsDistance(x1, y1, x2, y2)](#module_utils..calcPointsDistance) ⇒ <code>number</code>
    * [~inRange(val, min, max)](#module_utils..inRange) ⇒ <code>boolean</code>
    * [~isBallCollision(ball, brick)](#module_utils..isBallCollision) ⇒ <code>boolean</code>
    * [~randomInRange(min, max)](#module_utils..randomInRange) ⇒ <code>number</code>
    * [~randomSign()](#module_utils..randomSign) ⇒ <code>number</code>
    * [~randomColor()](#module_utils..randomColor) ⇒ <code>Array.&lt;number&gt;</code>
    * [~showView(el)](#module_utils..showView)

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
<a name="module_utils..showView"></a>

### utils~showView(el)
Show the given view and hide the others

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type | Description |
| --- | --- | --- |
| el | <code>jQuery</code> | jQuery container element |

<a name="actions/create_game_request.module_js"></a>

## js
<a name="actions/create_game_success.module_js"></a>

## js
<a name="actions/game_start.module_js"></a>

## js
<a name="actions/game_state_update.module_js"></a>

## js
<a name="actions/game_stop.module_js"></a>

## js
<a name="actions/join_game_request.module_js"></a>

## js

* [js](#actions/join_game_request.module_js)
    * [.JoinGameRequestAction](#actions/join_game_request.module_js.JoinGameRequestAction)
        * [new exports.JoinGameRequestAction(key)](#new_actions/join_game_request.module_js.JoinGameRequestAction_new)

<a name="actions/join_game_request.module_js.JoinGameRequestAction"></a>

### js.JoinGameRequestAction
**Kind**: static class of [<code>js</code>](#actions/join_game_request.module_js)  
<a name="new_actions/join_game_request.module_js.JoinGameRequestAction_new"></a>

#### new exports.JoinGameRequestAction(key)

| Param | Type |
| --- | --- |
| key | <code>string</code> | 

<a name="actions/move_paddle_left.module_js"></a>

## js
<a name="actions/move_paddle_right.module_js"></a>

## js
<a name="module_bodies/ball"></a>

## bodies/ball

* [bodies/ball](#module_bodies/ball)
    * [.Ball](#module_bodies/ball.Ball)
        * [.update(bodyObj)](#module_bodies/ball.Ball+update)
        * [.draw(s)](#module_bodies/ball.Ball+draw)

<a name="module_bodies/ball.Ball"></a>

### bodies/ball.Ball
Represents the ball

**Kind**: static class of [<code>bodies/ball</code>](#module_bodies/ball)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| x | <code>number</code> | horizontal position |
| y | <code>number</code> | vertical position |
| dx | <code>number</code> | horizontal speed |
| dy | <code>number</code> | vertical speed |


* [.Ball](#module_bodies/ball.Ball)
    * [.update(bodyObj)](#module_bodies/ball.Ball+update)
    * [.draw(s)](#module_bodies/ball.Ball+draw)

<a name="module_bodies/ball.Ball+update"></a>

#### ball.update(bodyObj)
Update body to match the server state

**Kind**: instance method of [<code>Ball</code>](#module_bodies/ball.Ball)  

| Param | Type |
| --- | --- |
| bodyObj | <code>object</code> | 

<a name="module_bodies/ball.Ball+draw"></a>

#### ball.draw(s)
Draw the ball on the provides 2D context

**Kind**: instance method of [<code>Ball</code>](#module_bodies/ball.Ball)  

| Param | Type |
| --- | --- |
| s | <code>Sketch</code> | 

<a name="module_bodies/brick"></a>

## bodies/brick

* [bodies/brick](#module_bodies/brick)
    * [.Brick](#module_bodies/brick.Brick)
        * [.move(dx, dy)](#module_bodies/brick.Brick+move)
        * [.draw()](#module_bodies/brick.Brick+draw)
    * [.BrickRow](#module_bodies/brick.BrickRow)
        * [new exports.BrickRow(rowIndex)](#new_module_bodies/brick.BrickRow_new)
        * [.moveDown()](#module_bodies/brick.BrickRow+moveDown)
        * [.isBallCollision(ball)](#module_bodies/brick.BrickRow+isBallCollision) ⇒ <code>Ball</code>
        * [.removeBrick(brick)](#module_bodies/brick.BrickRow+removeBrick)
        * [.isEmpty()](#module_bodies/brick.BrickRow+isEmpty) ⇒ <code>bool</code>
        * [.draw()](#module_bodies/brick.BrickRow+draw)

<a name="module_bodies/brick.Brick"></a>

### bodies/brick.Brick
Represents a brick

**Kind**: static class of [<code>bodies/brick</code>](#module_bodies/brick)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| x | <code>number</code> | horizontal position |
| height | <code>number</code> |  |
| width | <code>number</code> |  |
| color | <code>Array.&lt;number&gt;</code> |  |


* [.Brick](#module_bodies/brick.Brick)
    * [.move(dx, dy)](#module_bodies/brick.Brick+move)
    * [.draw()](#module_bodies/brick.Brick+draw)

<a name="module_bodies/brick.Brick+move"></a>

#### brick.move(dx, dy)
Move the ball to new position

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick.Brick)  

| Param | Type |
| --- | --- |
| dx | <code>int</code> | 
| dy | <code>int</code> | 

<a name="module_bodies/brick.Brick+draw"></a>

#### brick.draw()
Draw the brick on the screen

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick.Brick)  
<a name="module_bodies/brick.BrickRow"></a>

### bodies/brick.BrickRow
Represents a row of bricks

**Kind**: static class of [<code>bodies/brick</code>](#module_bodies/brick)  
**Properties**

| Name | Type |
| --- | --- |
| bricks | <code>Array.&lt;Brick&gt;</code> | 


* [.BrickRow](#module_bodies/brick.BrickRow)
    * [new exports.BrickRow(rowIndex)](#new_module_bodies/brick.BrickRow_new)
    * [.moveDown()](#module_bodies/brick.BrickRow+moveDown)
    * [.isBallCollision(ball)](#module_bodies/brick.BrickRow+isBallCollision) ⇒ <code>Ball</code>
    * [.removeBrick(brick)](#module_bodies/brick.BrickRow+removeBrick)
    * [.isEmpty()](#module_bodies/brick.BrickRow+isEmpty) ⇒ <code>bool</code>
    * [.draw()](#module_bodies/brick.BrickRow+draw)

<a name="new_module_bodies/brick.BrickRow_new"></a>

#### new exports.BrickRow(rowIndex)

| Param | Type | Description |
| --- | --- | --- |
| rowIndex | <code>number</code> | The index of row |

<a name="module_bodies/brick.BrickRow+moveDown"></a>

#### brickRow.moveDown()
Move the bricks in this row down 1 row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick.BrickRow)  
<a name="module_bodies/brick.BrickRow+isBallCollision"></a>

#### brickRow.isBallCollision(ball) ⇒ <code>Ball</code>
Checks if the ball colides with a brick in the row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick.BrickRow)  

| Param | Type |
| --- | --- |
| ball | <code>Ball</code> | 

<a name="module_bodies/brick.BrickRow+removeBrick"></a>

#### brickRow.removeBrick(brick)
Removes brick from the row

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick.BrickRow)  

| Param | Type |
| --- | --- |
| brick | <code>Brick</code> | 

<a name="module_bodies/brick.BrickRow+isEmpty"></a>

#### brickRow.isEmpty() ⇒ <code>bool</code>
Checks if the row is empty

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick.BrickRow)  
<a name="module_bodies/brick.BrickRow+draw"></a>

#### brickRow.draw()
Draws the bricks

**Kind**: instance method of [<code>BrickRow</code>](#module_bodies/brick.BrickRow)  
<a name="module_bodies/paddle"></a>

## bodies/paddle

* [bodies/paddle](#module_bodies/paddle)
    * [.Paddle](#module_bodies/paddle.Paddle)
        * [.update(bodyObj)](#module_bodies/paddle.Paddle+update)
        * [.draw(s)](#module_bodies/paddle.Paddle+draw)

<a name="module_bodies/paddle.Paddle"></a>

### bodies/paddle.Paddle
Represents paddle

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
    * [.update(bodyObj)](#module_bodies/paddle.Paddle+update)
    * [.draw(s)](#module_bodies/paddle.Paddle+draw)

<a name="module_bodies/paddle.Paddle+update"></a>

#### paddle.update(bodyObj)
Update body to match the server state

**Kind**: instance method of [<code>Paddle</code>](#module_bodies/paddle.Paddle)  

| Param | Type |
| --- | --- |
| bodyObj | <code>object</code> | 

<a name="module_bodies/paddle.Paddle+draw"></a>

#### paddle.draw(s)
Daw the paddle on the screen

**Kind**: instance method of [<code>Paddle</code>](#module_bodies/paddle.Paddle)  

| Param | Type |
| --- | --- |
| s | <code>Sketch</code> | 

<a name="module_bodies/score"></a>

## bodies/score

* [bodies/score](#module_bodies/score)
    * [.Score](#module_bodies/score.Score)
        * [.add()](#module_bodies/score.Score+add)
        * [.get()](#module_bodies/score.Score+get) ⇒ <code>number</code>
        * [.draw()](#module_bodies/score.Score+draw)

<a name="module_bodies/score.Score"></a>

### bodies/score.Score
Represents the user score

**Kind**: static class of [<code>bodies/score</code>](#module_bodies/score)  
**Properties**

| Name | Type |
| --- | --- |
| score | <code>number</code> | 
| color | <code>string</code> | 


* [.Score](#module_bodies/score.Score)
    * [.add()](#module_bodies/score.Score+add)
    * [.get()](#module_bodies/score.Score+get) ⇒ <code>number</code>
    * [.draw()](#module_bodies/score.Score+draw)

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
<a name="module_socket/client"></a>

## socket/client

* [socket/client](#module_socket/client)
    * [~WsClient](#module_socket/client..WsClient)
        * [.open()](#module_socket/client..WsClient+open)
        * [.onOpen()](#module_socket/client..WsClient+onOpen)
        * [.onClose()](#module_socket/client..WsClient+onClose)
        * [.onMessage()](#module_socket/client..WsClient+onMessage)
        * [.send(action)](#module_socket/client..WsClient+send)

<a name="module_socket/client..WsClient"></a>

### socket/client~WsClient
Websocket client

**Kind**: inner class of [<code>socket/client</code>](#module_socket/client)  
**Properties**

| Name | Type |
| --- | --- |
| ws | <code>WebSocket</code> | 


* [~WsClient](#module_socket/client..WsClient)
    * [.open()](#module_socket/client..WsClient+open)
    * [.onOpen()](#module_socket/client..WsClient+onOpen)
    * [.onClose()](#module_socket/client..WsClient+onClose)
    * [.onMessage()](#module_socket/client..WsClient+onMessage)
    * [.send(action)](#module_socket/client..WsClient+send)

<a name="module_socket/client..WsClient+open"></a>

#### wsClient.open()
Open connection

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  
<a name="module_socket/client..WsClient+onOpen"></a>

#### wsClient.onOpen()
Event handler for succesfull connection

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  
<a name="module_socket/client..WsClient+onClose"></a>

#### wsClient.onClose()
Event handler for connection loss

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  
<a name="module_socket/client..WsClient+onMessage"></a>

#### wsClient.onMessage()
Event handler for receicing messages

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  
<a name="module_socket/client..WsClient+send"></a>

#### wsClient.send(action)
Send an action to the server

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  

| Param | Type |
| --- | --- |
| action | <code>RequestAction</code> | 

<a name="module_views/connection_loss"></a>

## views/connection_loss
<a name="module_views/created_game"></a>

## views/created_game
<a name="module_views/created_game.show"></a>

### views/created_game.show(key)
Show the created game view

**Kind**: static method of [<code>views/created_game</code>](#module_views/created_game)  

| Param | Type | Description |
| --- | --- | --- |
| key | <code>string</code> | Game session key |

<a name="module_views/game_started"></a>

## views/game_started
<a name="module_views/game_stopped"></a>

## views/game_stopped
<a name="module_views/init_game"></a>

## views/init_game
<a name="module_views/loading"></a>

## views/loading
