/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import java.util.HashSet;
import java.util.Set;
import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Brick;
import nu.smashit.core.Player;

/**
 *
 * @author jodus
 */
public class GameStateUpdateAction implements ResponseAction {

    public Ball ball;
    public Player[] players;
    public Set<Brick> bricks;
    public int countDown;

    public GameStateUpdateAction(Ball ball, Player[] players, int countDown) {
        this.ball = ball;
        this.bricks = new HashSet<>();
        this.players = players;
        this.countDown = countDown;
    }

    public GameStateUpdateAction(Ball ball, Player[] players) {
        this(ball, players, 0);
    }

    public void addBrick(Brick b) {
        bricks.add(b);
    }
}
