package nu.smashit.socket.actions;

import nu.smashit.core.Game;
import nu.smashit.core.GameManager;
import nu.smashit.socket.Client;

public class CreateSingleplayerRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        GameManager gameSessionManager = GameManager.getInstance();
        try {
            Game gm = gameSessionManager.createSingleplayerGame(c);
            c.sendAction(new CreateSingleplayerSuccessAction(gm.getKey()));
            gm.startGame();
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }
}
