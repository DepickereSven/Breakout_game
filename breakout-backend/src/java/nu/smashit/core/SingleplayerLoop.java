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
            if (Collision.isWallCollision(ball)) {
                ball.inverseHozSpeed();
            } else if (Collision.isCeilingCollision(ball)) {
                ball.inverseVerSpeed();
            } else if (Collision.isFloorCollision(ball)) {
                gameSession.broadcastAction(new GameLossAction());
                gameSession.stopGame();
            } else {
                Player player = ((SingleplayerSession)gameSession).getPlayer();
                if (!ball.isGoingUp() && Collision.isCollision(ball, player.paddle)) {
                    if (Collision.isVerCollision(ball, player.paddle)) {
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
