package nu.smashit.data.dataobjects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import nu.smashit.core.bodies.Brick;
import nu.smashit.utils.Tools;

public class Score {

    private static final int START_POINTS = 100;
    private static final int DEATH_POINTS = 50;

    private int scoreID;
    private User userWon;
    private User userLost;
    private int points;
    private int time;
    private int pointsFactor;

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

    public void addOpponentDeath() {
        points += DEATH_POINTS;
    }

    public void subtractDeath() {
        points -= DEATH_POINTS;
    }

    public void addBrickSmash(Brick brick) {
        points += brick.type.getBrickPoints() * getPointsFactor();
    }

    @JsonIgnore
    public int getScoreID() {
        return scoreID;
    }

    @JsonIgnore
    public User getUserWon() {
        return userWon;
    }

    @JsonIgnore
    public User getUserLost() {
        return userLost;
    }

    @JsonProperty("p")
    public int getPoints() {
        return points;
    }

    @JsonIgnore
    public int getTime() {
        return time;
    }

    public void setUserWon(User userWon) {
        this.userWon = userWon;
    }

    public void setUserLost(User userLost) {
        this.userLost = userLost;
    }

    @JsonIgnore
    public int getPointsFactor() {
        return pointsFactor;
    }

    public void setPointsFactor(int pointsFactor) {
        this.pointsFactor = Tools.validateBetween(pointsFactor, 0, 100, 1);
    }

}
