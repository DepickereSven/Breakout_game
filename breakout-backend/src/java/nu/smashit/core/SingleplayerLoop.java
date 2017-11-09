package nu.smashit.core;

// @author Jonas

import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;

public class SingleplayerLoop extends GameLoop{

    public SingleplayerLoop(GameSession gm) {
        super(gm, Field.getSingleplayerInstance(50));
    }
    
   @Override
    public void run() {
       GameStateUpdateAction updateStateAction = new GameStateUpdateAction(ball, gameSession.players);

       for (Player p : gameSession.players) {
           p.paddle.move();
       }

        // Ball movement
        if (ball != null) {
            if (ball.isWallCollision()) {
                ball.inverseHozSpeed();
            }else if (ball.isCeilingCollision()){
                ball.inverseVerSpeed();
            }else if (ball.isFloorCollision()){
                gameSession.broadcastAction(new GameLossAction());
                gameSession.stopGame();
            } else {
                Player player = ((SingleplayerSession)gameSession).getPlayer();
                if (!ball.isGoingUp() && ball.isCollision(player.paddle)) {
                    if (ball.isVerCollision(player.paddle)) {
                        ball.inverseVerSpeed();
                    } else {
                        ball.inverseHozSpeed();
                    }
                }
            }

            ball.move();
       }

       if (field != null) {
           for (BrickRow br : field.getField()) {
               for (Brick b : br.row) {
                   if (b != null) {
                       updateStateAction.addBrick(b);
                   }
               }
           }
       }

        gameSession.broadcastAction(updateStateAction);
    }

}
