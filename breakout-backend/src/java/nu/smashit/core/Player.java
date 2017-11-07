package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class Player {

    public static enum PlayerType{ 
        PLAYER_1,
        PLAYER_2
    }

    @JsonIgnore
    public final Client client;
    public final Paddle paddle;
    public final Score score;

    public Player(Client client, PlayerType type) {
        this.client = client;
        this.paddle = new Paddle(type == PlayerType.PLAYER_1 ? Paddle.PLAYER_1_Y : Paddle.PLAYER_2_Y);
        this.score = new Score();
    }
    
}
