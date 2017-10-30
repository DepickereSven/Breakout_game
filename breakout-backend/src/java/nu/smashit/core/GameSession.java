/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import java.util.Timer;
import java.util.TimerTask;
import nu.smashit.socket.Action;
import nu.smashit.socket.Client;
import nu.smashit.socket.GameStartAction;
import nu.smashit.socket.GameStopAction;

/**
 *
 * @author jodus
 */
public class GameSession {

    private final Client[] clients;
    private final Paddle paddle;
    public final String key;
    public final Timer timer;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.paddle = new Paddle();

        this.timer = new Timer();
    }

    public void join(Client c) {
        if (clients[1] != null) {
            throw new Error("GameSession full");
        }
        int i = clients[0] == null ? 0 : 1;
        clients[i] = c;
        c.setGame(this);
    }

    private void broadcastAction(Action a) {
        for (Client c : clients) {
            c.sendAction(a);
        }
    }

    public void startGame() {
        broadcastAction(new GameStartAction());
    }

    public void stopGame() {
        broadcastAction(new GameStopAction());

        for (Client c : clients) {
            c.removeGame();
        }
        GameSessionManager.getInstance().removeGame(key);
    }
}
