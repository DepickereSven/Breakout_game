package nu.smashit.data.dataobjects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import nu.smashit.core.bodies.Brick;
import nu.smashit.utils.Tools;

public class Score {

    static final int START_POINTS = 100;
    static final int DEATH_POINTS = 50;

    private int scoreID;
    private User userWon;
    private User userLost;
    private int points;
    private int time;
    private double pointsFactor;

    public Score() {
        this(0, null, null, START_POINTS, 0);
    }

    public Score(int scoreID, User userWon, User userLost, int points, int time) {
        this.scoreID = scoreID;
        this.userWon = userWon;
        this.userLost = userLost;
        this.points = points;
        this.time = time;
        this.pointsFactor = 1;
    }

    @JsonIgnore
    public boolean isAlive() {
        return getPoints() > 0;
    }

    @JsonIgnore
    public int getScoreID() {
        return scoreID;
    }

    private class SmallUser {

        public String name;
        public String country;

        public SmallUser(String name, String country) {
            this.name = name;
            this.country = country;
        }
    }

    @JsonIgnore
    public User getUserWon() {
        return userWon;
    }

    @JsonProperty("user_won")
    public SmallUser getSmallUserWon() {
        return new SmallUser(userWon.getUsername(), userWon.getCountry());
    }

    @JsonIgnore
    public User getUserLost() {
        return userLost;
    }

    @JsonProperty("user_lost")
    public SmallUser getSmallLost() {
        return new SmallUser(userLost.getUsername(), userLost.getCountry());
    }

    public int getPoints() {
        return points;
    }

    public int getTime() {
        return time;
    }

    @JsonIgnore
    public double getPointsFactor() {
        return pointsFactor;
    }

    public void addOpponentDeath() {
        points += DEATH_POINTS;
    }

    public void subtractDeath() {
        points -= DEATH_POINTS;
    }

    public void addBrickSmash(Brick brick) {
        points += brick.type.getBrickPoints() * getPointsFactor();
    }

    public void setUserWon(User userWon) {
        this.userWon = userWon;
    }

    public void setUserLost(User userLost) {
        this.userLost = userLost;
    }

    public void setPointsFactor(double pointsFactor) {
        this.pointsFactor = Tools.validateBetween(pointsFactor, 0, 100, 1);
    }

    public void setTime(int time) {
        this.time = time;
    }

}
