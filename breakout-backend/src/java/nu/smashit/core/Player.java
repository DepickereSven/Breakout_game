package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nu.smashit.core.bodies.Paddle;
import nu.smashit.data.dataobjects.Score;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Player {

    public static enum PlayerType {
        PLAYER_1,
        PLAYER_2
    }

    private final Client client;
    private final Paddle paddle;
    private final Score score;
    private boolean ready;

    public Player(Client client, PlayerType type) {
        this.client = client;
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

    public String getClientId() {
        return getClient().getShortId();
    }

    public int getScorePoints() {
        return getScore().getPoints();
    }

    @JsonIgnore
    public Client getClient() {
        return client;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    @JsonIgnore
    public Score getScore() {
        return score;
    }
    
}
