/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("b")
    public Ball ball;
    @JsonProperty("p")
    public Player[] players;
    @JsonProperty("br")
    public Set<Brick> bricks;
    @JsonProperty("c")
    public int countDown;
    @JsonProperty("tm")
    public int time;

    public GameStateUpdateAction(Ball ball, Player[] players, int countDown, int time) {
        this.ball = ball;
        this.bricks = new HashSet<>();
        this.players = players;
        this.countDown = countDown;
        this.time = time;
    }

    public void addBrick(Brick b) {
        bricks.add(b);
    }
}
