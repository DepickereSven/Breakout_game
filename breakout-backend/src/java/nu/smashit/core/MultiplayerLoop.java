package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Field;
import nu.smashit.data.Repositories;
import nu.smashit.data.dataobjects.Score;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;
import nu.smashit.socket.actions.OpponentDeathAction;
import nu.smashit.socket.actions.PlayerDeathAction;
import nu.smashit.socket.actions.UpdateSmashbitAction;

public class MultiplayerLoop extends GameLoop {

    public MultiplayerLoop(MultiplayerGame gm) {
        super(gm, Field.getMultiplayerInstance());
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateState) {
        // Ball movement
        if (getBall() != null) {
            MultiplayerGame gm = (MultiplayerGame) getGameSession();
            Player scoredPlayer = null;
            Player lostPlayer = null;

            if (Collision.isCeilingCollision(getBall())) {
                lostPlayer = gm.getTopPlayer();
                scoredPlayer = gm.getBottomPlayer();
            } else if (Collision.isFloorCollision(getBall())) {
                lostPlayer = gm.getBottomPlayer();
                scoredPlayer = gm.getTopPlayer();
            } else if (runPaddleCollision(updateState)) {
            } else if (runBrickCollision(updateState)) {
            } else if (Collision.isWallCollision(getBall())) {
                getBall().inverseHorSpeed();
            }

            if (scoredPlayer != null && lostPlayer != null) {
                lostPlayer.getScore().subtractDeath();
                scoredPlayer.getScore().addOpponentDeath();

                if (!lostPlayer.getScore().isAlive()) {
                    gameEnded(scoredPlayer, lostPlayer);
                    return;
                }

                scoredPlayer.sendAction(new OpponentDeathAction());
                lostPlayer.sendAction(new PlayerDeathAction());

                int startPos;
                if (lostPlayer == gm.getBottomPlayer()) {
                    startPos = Ball.Y_START_POS_BOTTOM;
                } else {
                    startPos = Ball.Y_START_POS_TOP;
                }
                getBall().resetToPos(startPos);
            }
        }
    }

    private void gameEnded(Player playerWon, Player playerLost) {
        Score score = playerWon.getScore();

        playerWon.sendAction(new GameVictoryAction(score.getPoints()));
        playerLost.sendAction(new GameLossAction());
        getGameSession().stopGame();

        User userWon = playerWon.getUser();
        User userLost = playerLost.getUser();

        score.setUserLost(userLost);
        score.setUserWon(userWon);
        score.setTime(getGameSession().getTime());
        Repositories.getScoreRepository().addScore(score);

        userWon.setSmashbit(userWon.getSmashbit() + score.getPoints());
        Repositories.getUserRepository().updateSmashbit(userWon);
        playerWon.sendAction(new UpdateSmashbitAction(userWon.getSmashbit()));
    }

}
