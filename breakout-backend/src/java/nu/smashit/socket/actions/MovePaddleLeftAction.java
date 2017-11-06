/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.GameSession;
import nu.smashit.core.Paddle;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class MovePaddleLeftAction implements RequestAction {

    @Override
    public void handler(Client c) {
        if (c.isInGame()) {
            GameSession gm = c.getGame();
            Paddle p = gm.getPlayer(c).paddle;
            if (p.x > 0) {
                p.moveLeft();
            }
        }
    }

}
