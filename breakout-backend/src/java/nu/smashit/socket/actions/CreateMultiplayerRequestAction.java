package nu.smashit.socket.actions;

import nu.smashit.core.Game;
import nu.smashit.core.GameManager;
import nu.smashit.socket.Client;

public class CreateMultiplayerRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        GameManager gameSessionManager = GameManager.getInstance();
        try {
            Game gm = gameSessionManager.createMultiplayerGame(c.getUser());
            c.sendAction(new CreateMultiplayerSuccessAction(gm.getKey()));
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }
}
