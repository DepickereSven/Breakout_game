/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.socket.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nu.smashit.core.bodies.Ball;
import nu.smashit.core.bodies.Brick;
import nu.smashit.core.bodies.Paddle;
import nu.smashit.core.power.Power;

/**
 *
 * @author jodus
 */
public class GameStateUpdateAction implements ResponseAction {

    @JsonProperty("b")
    public Ball ball;
    @JsonProperty("p")
    @JsonInclude(Include.NON_DEFAULT)
    public List<Paddle> paddles;
    @JsonProperty("s")
    @JsonInclude(Include.NON_DEFAULT)
    public List<Integer> scores;
    @JsonProperty("br")
    @JsonInclude(Include.NON_DEFAULT)
    public Set<Brick> bricks;
    @JsonProperty("c")
    @JsonInclude(Include.NON_DEFAULT)
    public int countDown;
    @JsonProperty("t")
    @JsonInclude(Include.NON_DEFAULT)
    public int time;
    @JsonProperty("pw")
    @JsonInclude(Include.NON_DEFAULT)
    public List<Integer> powers;

    public GameStateUpdateAction() {
        this(null, 0);
    }

    public GameStateUpdateAction(Ball ball, int countDown) {
        this.ball = ball;
        this.bricks = new HashSet<>();
        this.paddles = new ArrayList<>(2);
        this.powers = new ArrayList<>();
        this.countDown = countDown;
    }

    public void addBrick(Brick b) {
        bricks.add(b);
    }

    public void addPaddle(Paddle p) {
        paddles.add(p);
    }

    public void addScores(List scores) {
        this.scores = scores;
    }

    public void addPower(int power) {
        powers.add(power);
    }

    public void reverseState() {
        for (Paddle p : paddles) {
            p.reverseY();
        }
        Collections.reverse(paddles);
        for (Brick b : bricks) {
            b.reverseY();
        }
        if (scores != null) {
            Collections.reverse(scores);
        }
        ball.reverseY();

    }

    public void setTime(int time) {
        this.time = time;
    }
}
