package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import nu.smashit.core.bodies.Paddle;
import nu.smashit.data.dataobjects.Score;
import nu.smashit.data.dataobjects.User;
import nu.smashit.socket.actions.ResponseAction;

/**
 *
 * @author jodus
 */
@JsonPropertyOrder({ "paddle", "clientId", "scorePoints" })
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Player {

    public static enum PlayerType {
        PLAYER_1,
        PLAYER_2
    }

    private final User user;
    private final Paddle paddle;
    private final Score score;
    private boolean ready;

    public Player(User user, PlayerType type) {
        this.user = user;
        this.paddle = new Paddle(type == PlayerType.PLAYER_1 ? Paddle.PLAYER_1_Y : Paddle.PLAYER_2_Y);
        this.score = new Score();
        this.ready = false;
    }

    @JsonIgnore
    public boolean isReady() {
        return ready;
    }

    @JsonIgnore
    public void ready() {
        ready = true;
    }
    
    public Paddle getPaddle() {
        return paddle;
    }
    
    public String getClientId() {
        return getUser().getClient().getShortId();
    }

    public int getScorePoints() {
        return getScore().getPoints();
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    @JsonIgnore
    public Score getScore() {
        return score;
    }
    
    public void sendAction(ResponseAction a) {
        getUser().getClient().sendAction(a);
    }
    
}
