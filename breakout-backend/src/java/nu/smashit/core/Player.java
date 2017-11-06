/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import com.fasterxml.jackson.annotation.JsonIgnore;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class Player {

    public final static String PLAYER_1 = "PLAYER_1";
    public final static String PLAYER_2 = "PLAYER_2";

    @JsonIgnore
    public final Client client;

    public final Paddle paddle;
    public final Score score;

    public Player(Client client, String type) {
        this.client = client;
        this.paddle = new Paddle(type == PLAYER_1 ? Paddle.PLAYER_1_Y : Paddle.PLAYER_2_Y);
        this.score = new Score();
    }
}
