/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.Game;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class PlayerReadyAction implements RequestAction {

    @Override
    public void handler(Client c) {
        User u = c.getUser();
        if (u.isInGame()) {
            Game gm = u.getGame();
            gm.playerReady(u);
        }
    }

}
