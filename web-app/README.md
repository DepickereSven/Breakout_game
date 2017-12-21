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
<dt><a href="#module_player">player</a></dt>
<dd></dd>
<dt><a href="#module_sketch">sketch</a></dt>
<dd></dd>
<dt><a href="#module_utils">utils</a></dt>
<dd></dd>
<dt><a href="#actions/cancel_multiplayer_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/connection_success.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/create_multiplayer_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/create_game_success.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/create_singleplayer_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/create_game_success.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_loss.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_start.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_state_update.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_stop.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/game_victory.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/join_private_game_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/join_public_game_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/move_paddle_start.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/move_paddle_stop.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/scores_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/scores_response.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/user_login_failure.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/user_login_request.module_js">js</a></dt>
<dd></dd>
<dt><a href="#actions/user_login_success.module_js">js</a></dt>
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
</dl>

<a name="module_constants"></a>

## constants

* [constants](#module_constants)
    * [.API_URL](#module_constants.API_URL) : <code>string</code>
    * [.C_HEIGHT](#module_constants.C_HEIGHT) : <code>number</code>
    * [.C_WIDTH](#module_constants.C_WIDTH) : <code>number</code>
    * [.IS_ANDROID_APP](#module_constants.IS_ANDROID_APP) : <code>string</code>
    * [.LOSS_QUOTES](#module_constants.LOSS_QUOTES) : <code>Array.&lt;object&gt;</code>
    * [.WIN_QUOTES](#module_constants.WIN_QUOTES) : <code>Array.&lt;object&gt;</code>
    * [.SCREEN_ANIMATION_TIME](#module_constants.SCREEN_ANIMATION_TIME) : <code>number</code>
    * [.G_CLIENT_ID](#module_constants.G_CLIENT_ID) : <code>string</code>

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
<a name="module_constants.IS_ANDROID_APP"></a>

### constants.IS_ANDROID_APP : <code>string</code>
Is the current client the webview in the android app

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.LOSS_QUOTES"></a>

### constants.LOSS_QUOTES : <code>Array.&lt;object&gt;</code>
Array of quotes about losing

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.WIN_QUOTES"></a>

### constants.WIN_QUOTES : <code>Array.&lt;object&gt;</code>
Array of quotes about winning

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.SCREEN_ANIMATION_TIME"></a>

### constants.SCREEN_ANIMATION_TIME : <code>number</code>
The length of the screen transition animation

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_constants.G_CLIENT_ID"></a>

### constants.G_CLIENT_ID : <code>string</code>
Google application client id

**Kind**: static property of [<code>constants</code>](#module_constants)  
<a name="module_gameLoop"></a>

## gameLoop

* [gameLoop](#module_gameLoop)
    * [.GameLoop](#module_gameLoop.GameLoop)
        * [.createPlayers(playerCount)](#module_gameLoop.GameLoop+createPlayers)
        * [.updatePaddles(paddles)](#module_gameLoop.GameLoop+updatePaddles)
        * [.updateScores(scores)](#module_gameLoop.GameLoop+updateScores)
        * [.updateBricks(bricks)](#module_gameLoop.GameLoop+updateBricks)
        * [.updateBall(ball)](#module_gameLoop.GameLoop+updateBall)
        * [.run()](#module_gameLoop.GameLoop+run)

<a name="module_gameLoop.GameLoop"></a>

### gameLoop.GameLoop
GameLoop provides the state and drawing for the sketch

**Kind**: static class of [<code>gameLoop</code>](#module_gameLoop)  
**Properties**

| Name | Type |
| --- | --- |
| paddles | <code>Array.&lt;Paddle&gt;</code> | 
| ball | <code>Ball</code> | 


* [.GameLoop](#module_gameLoop.GameLoop)
    * [.createPlayers(playerCount)](#module_gameLoop.GameLoop+createPlayers)
    * [.updatePaddles(paddles)](#module_gameLoop.GameLoop+updatePaddles)
    * [.updateScores(scores)](#module_gameLoop.GameLoop+updateScores)
    * [.updateBricks(bricks)](#module_gameLoop.GameLoop+updateBricks)
    * [.updateBall(ball)](#module_gameLoop.GameLoop+updateBall)
    * [.run()](#module_gameLoop.GameLoop+run)

<a name="module_gameLoop.GameLoop+createPlayers"></a>

#### gameLoop.createPlayers(playerCount)
Create players objects for each player

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  

| Param | Type |
| --- | --- |
| playerCount | <code>number</code> | 

<a name="module_gameLoop.GameLoop+updatePaddles"></a>

#### gameLoop.updatePaddles(paddles)
Update paddles to current state

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  

| Param | Type |
| --- | --- |
| paddles | <code>Array.&lt;Array.&lt;number&gt;&gt;</code> | 

<a name="module_gameLoop.GameLoop+updateScores"></a>

#### gameLoop.updateScores(scores)
Update scores to current state

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  

| Param | Type |
| --- | --- |
| scores | <code>Array.&lt;number&gt;</code> | 

<a name="module_gameLoop.GameLoop+updateBricks"></a>

#### gameLoop.updateBricks(bricks)
Update bricks to current state or create new bricks if they don't exist already

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  

| Param | Type |
| --- | --- |
| bricks | <code>Array.&lt;object&gt;</code> | 

<a name="module_gameLoop.GameLoop+updateBall"></a>

#### gameLoop.updateBall(ball)
Update the ball to match the server state

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  

| Param | Type |
| --- | --- |
| ball | <code>Array.&lt;number&gt;</code> | 

<a name="module_gameLoop.GameLoop+run"></a>

#### gameLoop.run()
Draws the current state onto the provided sketch

**Kind**: instance method of [<code>GameLoop</code>](#module_gameLoop.GameLoop)  
<a name="module_initialize"></a>

## initialize
<a name="module_player"></a>

## player
<a name="module_sketch"></a>

## sketch
<a name="module_utils"></a>

## utils

* [utils](#module_utils)
    * [~inRange(val, min, max)](#module_utils..inRange) ⇒ <code>boolean</code>
    * [~randomInRange(min, max)](#module_utils..randomInRange) ⇒ <code>number</code>
    * [~randomSign()](#module_utils..randomSign) ⇒ <code>number</code>
    * [~randomColor()](#module_utils..randomColor) ⇒ <code>Array.&lt;number&gt;</code>

<a name="module_utils..inRange"></a>

### utils~inRange(val, min, max) ⇒ <code>boolean</code>
Check if value is between min and max

**Kind**: inner method of [<code>utils</code>](#module_utils)  

| Param | Type |
| --- | --- |
| val | <code>number</code> | 
| min | <code>number</code> | 
| max | <code>number</code> | 

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
<a name="actions/cancel_multiplayer_request.module_js"></a>

## js
<a name="actions/connection_success.module_js"></a>

## js
<a name="actions/create_multiplayer_request.module_js"></a>

## js
<a name="actions/create_game_success.module_js"></a>

## js
<a name="actions/create_singleplayer_request.module_js"></a>

## js
<a name="actions/create_game_success.module_js"></a>

## js
<a name="actions/game_loss.module_js"></a>

## js
<a name="actions/game_start.module_js"></a>

## js
<a name="actions/game_state_update.module_js"></a>

## js
<a name="actions/game_stop.module_js"></a>

## js
<a name="actions/game_victory.module_js"></a>

## js
<a name="actions/join_private_game_request.module_js"></a>

## js
<a name="actions/join_public_game_request.module_js"></a>

## js
<a name="actions/move_paddle_start.module_js"></a>

## js
<a name="actions/move_paddle_stop.module_js"></a>

## js
<a name="actions/scores_request.module_js"></a>

## js
<a name="actions/scores_response.module_js"></a>

## js
<a name="actions/user_login_failure.module_js"></a>

## js
<a name="actions/user_login_request.module_js"></a>

## js
<a name="actions/user_login_success.module_js"></a>

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
| h | <code>number</code> | height |
| w | <code>number</code> | width |


* [.Ball](#module_bodies/ball.Ball)
    * [.update(bodyObj)](#module_bodies/ball.Ball+update)
    * [.draw(s)](#module_bodies/ball.Ball+draw)

<a name="module_bodies/ball.Ball+update"></a>

#### ball.update(bodyObj)
Update body to match the server state

**Kind**: instance method of [<code>Ball</code>](#module_bodies/ball.Ball)  

| Param | Type |
| --- | --- |
| bodyObj | <code>Array.&lt;number&gt;</code> | 

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
        * [.update(bodyObj)](#module_bodies/brick.Brick+update)
        * [.isBroken()](#module_bodies/brick.Brick+isBroken) ⇒ <code>boolean</code>
        * [.draw(s)](#module_bodies/brick.Brick+draw)

<a name="module_bodies/brick.Brick"></a>

### bodies/brick.Brick
Represents a brick

**Kind**: static class of [<code>bodies/brick</code>](#module_bodies/brick)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| id | <code>string</code> |  |
| type | <code>string</code> |  |
| x | <code>number</code> | horizontal position |
| h | <code>number</code> | height |
| w | <code>number</code> | width |
| lives | <code>number</code> |  |
| color | <code>Array.&lt;number&gt;</code> |  |


* [.Brick](#module_bodies/brick.Brick)
    * [.update(bodyObj)](#module_bodies/brick.Brick+update)
    * [.isBroken()](#module_bodies/brick.Brick+isBroken) ⇒ <code>boolean</code>
    * [.draw(s)](#module_bodies/brick.Brick+draw)

<a name="module_bodies/brick.Brick+update"></a>

#### brick.update(bodyObj)
Update body to match the server state

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick.Brick)  

| Param | Type |
| --- | --- |
| bodyObj | <code>object</code> | 

<a name="module_bodies/brick.Brick+isBroken"></a>

#### brick.isBroken() ⇒ <code>boolean</code>
Check if the brick is broken

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick.Brick)  
<a name="module_bodies/brick.Brick+draw"></a>

#### brick.draw(s)
Draw the brick on the provides 2D context

**Kind**: instance method of [<code>Brick</code>](#module_bodies/brick.Brick)  

| Param | Type |
| --- | --- |
| s | <code>Sketch</code> | 

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

| Name | Type | Description |
| --- | --- | --- |
| h | <code>number</code> | height |
| w | <code>number</code> | width |
| borderRadius | <code>number</code> |  |
| x | <code>number</code> |  |
| y | <code>number</code> |  |
| color | <code>string</code> |  |


* [.Paddle](#module_bodies/paddle.Paddle)
    * [.update(bodyObj)](#module_bodies/paddle.Paddle+update)
    * [.draw(s)](#module_bodies/paddle.Paddle+draw)

<a name="module_bodies/paddle.Paddle+update"></a>

#### paddle.update(bodyObj)
Update body to match the server state

**Kind**: instance method of [<code>Paddle</code>](#module_bodies/paddle.Paddle)  

| Param | Type |
| --- | --- |
| bodyObj | <code>Array.&lt;number&gt;</code> | 

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
        * [.update()](#module_bodies/score.Score+update)
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
    * [.update()](#module_bodies/score.Score+update)
    * [.draw()](#module_bodies/score.Score+draw)

<a name="module_bodies/score.Score+update"></a>

#### score.update()
**Kind**: instance method of [<code>Score</code>](#module_bodies/score.Score)  
<a name="module_bodies/score.Score+draw"></a>

#### score.draw()
Draws the score on the screen

**Kind**: instance method of [<code>Score</code>](#module_bodies/score.Score)  
<a name="module_socket/client"></a>

## socket/client

* [socket/client](#module_socket/client)
    * [~WsClient](#module_socket/client..WsClient)
        * [.open(callback)](#module_socket/client..WsClient+open)
        * [.setClientId()](#module_socket/client..WsClient+setClientId)
        * [.onClose()](#module_socket/client..WsClient+onClose)
        * [.onMessage()](#module_socket/client..WsClient+onMessage)
        * [.send(action)](#module_socket/client..WsClient+send)
        * [.reset(callback)](#module_socket/client..WsClient+reset)

<a name="module_socket/client..WsClient"></a>

### socket/client~WsClient
Websocket client

**Kind**: inner class of [<code>socket/client</code>](#module_socket/client)  
**Properties**

| Name | Type | Description |
| --- | --- | --- |
| ws | <code>WebSocket</code> |  |
| clientId | <code>String</code> | UUID that the socket server gives to our client with the ConnectionSuccessAction |


* [~WsClient](#module_socket/client..WsClient)
    * [.open(callback)](#module_socket/client..WsClient+open)
    * [.setClientId()](#module_socket/client..WsClient+setClientId)
    * [.onClose()](#module_socket/client..WsClient+onClose)
    * [.onMessage()](#module_socket/client..WsClient+onMessage)
    * [.send(action)](#module_socket/client..WsClient+send)
    * [.reset(callback)](#module_socket/client..WsClient+reset)

<a name="module_socket/client..WsClient+open"></a>

#### wsClient.open(callback)
Open connection

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  

| Param | Type |
| --- | --- |
| callback | <code>function</code> | 

<a name="module_socket/client..WsClient+setClientId"></a>

#### wsClient.setClientId()
Set the clientId
Only done once

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

<a name="module_socket/client..WsClient+reset"></a>

#### wsClient.reset(callback)
Reset websocket connection

**Kind**: instance method of [<code>WsClient</code>](#module_socket/client..WsClient)  

| Param | Type |
| --- | --- |
| callback | <code>function</code> | 

