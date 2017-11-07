package nu.smashit.socket.actions;

import nu.smashit.core.GameSession;
import nu.smashit.core.GameSessionManager;
import nu.smashit.socket.Client;

public class CreateSingleplayerRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        GameSessionManager gameSessionManager = GameSessionManager.getInstance();
        try {
            GameSession gm = gameSessionManager.createSingleplayerGame(c);
            c.sendAction(new CreateSingleplayerSuccessAction(gm.getKey()));
            gm.startGame();
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }
}
