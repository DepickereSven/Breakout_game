/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.GameSession;
import nu.smashit.core.GameSessionManager;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class CreateGameRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        GameSessionManager gameSessionManager = GameSessionManager.getInstance();
        try {
            GameSession gm = gameSessionManager.createGame(c);
            c.sendAction(new CreateGameSuccessAction(gm.getKey()));
        } catch (Error err) {
            c.sendAction(new CreateGameFailureAction(err.getMessage()));
        }
    }
}
