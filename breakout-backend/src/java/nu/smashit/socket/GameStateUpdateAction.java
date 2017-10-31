/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket;

import java.util.HashSet;
import java.util.Set;
import nu.smashit.core.Body;

/**
 *
 * @author jodus
 */
public class GameStateUpdateAction implements ResponseAction {

    public Set<Body> bodies;

    public GameStateUpdateAction() {
        this.bodies = new HashSet<>();
    }

    public void addBody(Body b) {
        bodies.add(b);
    }
}
