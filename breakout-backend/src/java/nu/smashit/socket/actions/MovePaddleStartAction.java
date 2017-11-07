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
public class MovePaddleStartAction implements RequestAction {

    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    public String direction;

    @Override
    public void handler(Client c) {
        if (c.isInGame()) {
            GameSession gm = c.getGame();
            Paddle p = gm.getPlayer(c).paddle;

            if (direction.equals(LEFT)) {
                p.goLeft();
            } else if (direction.equals(RIGHT)) {
                p.goRight();
            }
        }
    }

}
