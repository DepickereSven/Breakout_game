package nu.smashit.core;

// @author Jonas
import nu.smashit.core.bodies.Field;
import nu.smashit.data.Repositories;
import nu.smashit.data.dataobjects.Score;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.actions.GameLossAction;
import nu.smashit.socket.actions.GameStateUpdateAction;
import nu.smashit.socket.actions.GameVictoryAction;
import nu.smashit.socket.actions.OpponentDeathAction;
import nu.smashit.socket.actions.PlayerDeathAction;

public class MultiplayerLoop extends GameLoop {

    public MultiplayerLoop(MultiplayerGame gm) {
        super(gm, Field.getMultiplayerInstance());
    }

    @Override
    protected void runLoop(GameStateUpdateAction updateState) {
        // Ball movement
        if (getBall() != null) {
            Player scoredPlayer = null;
            Player lostPlayer = null;

            if (Collision.isCeilingCollision(getBall())) {
                lostPlayer = ((MultiplayerGame) getGameSession()).getTopPlayer();
                scoredPlayer = ((MultiplayerGame) getGameSession()).getBottomPlayer();
            } else if (Collision.isFloorCollision(getBall())) {
                lostPlayer = ((MultiplayerGame) getGameSession()).getBottomPlayer();
                scoredPlayer = ((MultiplayerGame) getGameSession()).getTopPlayer();
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

                getBall().reset();
            }

            getBall().move();
        }
    }

    private void gameEnded(Player playerWon, Player playerLost) {
        playerWon.sendAction(new GameVictoryAction());
        playerLost.sendAction(new GameLossAction());
        getGameSession().stopGame();
        
        Score score = playerWon.getScore();
        User userWon = playerWon.getUser();
        User userLost = playerLost.getUser();
        
        score.setUserLost(userLost);
        score.setUserWon(userWon);
        score.setTime(getGameSession().getTime());
        Repositories.getScoreRepository().addScore(score);
        
        userWon.setSmashbit(userWon.getSmashbit() + score.getPoints());
        Repositories.getUserRepository().updateSmashbit(userWon);
    }

}
