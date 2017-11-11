/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.Game;
import nu.smashit.core.GameManager;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class JoinPrivateGameRequestAction implements RequestAction {

    public String key;

    @Override
    public void handler(Client c) {
        GameManager gameSessionManager = GameManager.getInstance();
        try {
            Game gm = gameSessionManager.joinPrivateMultiplayerGame(key, c);
            c.sendAction(new JoinGameSuccessAction(gm.getKey()));
            if (gm.isFull()) {
                gm.startGame();
            }
        } catch (Error err) {
            c.sendAction(new JoinGameFailureAction(err.getMessage()));
        }
    }
}
