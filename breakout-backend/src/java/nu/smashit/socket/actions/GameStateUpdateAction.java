/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import java.util.HashSet;
import java.util.Set;
import nu.smashit.core.Body;
import nu.smashit.core.Player;

/**
 *
 * @author jodus
 */
public class GameStateUpdateAction implements ResponseAction {

    public Player[] players;
    public Set<Body> bodies;

    public GameStateUpdateAction(Player[] players) {
        this.bodies = new HashSet<>();
        this.players = players;
    }

    public void addBody(Body b) {
        bodies.add(b);
    }
}
