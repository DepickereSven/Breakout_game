package nu.smashit.core;

// @author Jonas
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;

public class SingleplayerLoop extends GameLoop {

    public SingleplayerLoop(GameSession gm) {
        super(gm, Field.getSingleplayerInstance(50));
    }

    @Override
    public void run() {
        GameStateUpdateAction updateStateAction = new GameStateUpdateAction(ball, gameSession.players);

        // Move paddles to position (Player controls)
        for (Player p : gameSession.players) {
            p.paddle.move();
        }

        if (firstRun) {
            for (BrickRow br : field.brickRows) {
                for (Brick b : br.bricks) {
                    if (b != null) {
                        updateStateAction.addBrick(b);
                    }
                }
            }
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
            } else if (runPaddleCollision(updateStateAction)) {
            } else if (runBrickCollision(updateStateAction)) {
            }

            ball.move();
        }

        gameSession.broadcastAction(updateStateAction);

        if (firstRun) {
            firstRun = false;
        }
    }

}
