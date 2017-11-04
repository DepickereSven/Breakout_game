/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import nu.smashit.core.GameCanvas;
import nu.smashit.core.GameSession;
import nu.smashit.core.Paddle;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class MovePaddleRightAction implements RequestAction {

    @Override
    public void handler(Client c) {
        if (c.isInGame()) {
            GameSession gm = c.getGame();
            Paddle p = gm.getPaddle(c);
            if (p.x < GameCanvas.WIDTH - p.width) {
                p.move(7, 0);
            }
        }
    }

}