package nu.smashit.socket.actions;

import nu.smashit.core.GameSession;
import nu.smashit.core.GameSessionManager;
import nu.smashit.socket.Client;

public class CreateMultiplayerRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        GameSessionManager gameSessionManager = GameSessionManager.getInstance();
        try {
            GameSession gm = gameSessionManager.createMultiplayerGame(c);
            c.sendAction(new CreateMultiplayerSuccessAction(gm.getKey()));
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }
}
