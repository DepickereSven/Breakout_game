package nu.smashit.core;

// @author Jonas

import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;

public class SingleplayerLoop extends GameLoop{

    public SingleplayerLoop(GameSession gm) {
        super(gm);
    }
    
   @Override
    public void run() {
       GameStateUpdateAction updateStateAction = new GameStateUpdateAction(gameSession.players);

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
            updateStateAction.addBody(ball);
        }

        gameSession.broadcastAction(updateStateAction);
    }

}
