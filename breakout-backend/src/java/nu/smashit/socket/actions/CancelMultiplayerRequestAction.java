/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.Game;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class CancelMultiplayerRequestAction implements RequestAction {

    @Override
    public void handler(Client c) {
        if (c.getUser().isInGame()) {
            Game g = c.getUser().getGame();
            if (!g.isFull()) {
                g.stopGame();
            }
        }
    }

}
