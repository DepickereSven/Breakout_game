/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author jodus
 */
public class Score {

    private static final int DEATH_POINTS = 50;

    private double points;

    Score() {
        points = 100;
    }

    @JsonIgnore
    public boolean isAlive() {
        return points > 0;
    }

    public void addOpponentDeath() {
        points += DEATH_POINTS;
    }

    public void subtractDeath() {
        points -= DEATH_POINTS;
    }

    public double getPoints() {
        return points;
    }
}
