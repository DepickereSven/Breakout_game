/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nu.smashit.core;

import java.util.Timer;
import java.util.TimerTask;
import nu.smashit.socket.Client;
import nu.smashit.socket.GameStartAction;
import nu.smashit.socket.GameStopAction;
import nu.smashit.socket.GameStateUpdateAction;
import nu.smashit.socket.ResponseAction;

/**
 *
 * @author jodus
 */
public class GameSession {

    private final Client[] clients;
    public final String key;
    private final Timer timer;
    private final int updateInterval;

    public final Paddle paddle;

    GameSession(String key) {
        this.key = key;
        this.clients = new Client[2];
        this.timer = new Timer();
        this.updateInterval = 33;

        this.paddle = new Paddle();
    }

    public void join(Client c) {
        if (clients[1] != null) {
            throw new Error("GameSession full");
        }
        int i = clients[0] == null ? 0 : 1;
        clients[i] = c;
        c.setGame(this);
    }

    private void broadcastAction(ResponseAction a) {
        for (Client c : clients) {
            c.sendAction(a);
        }
    }

    public void startGame() {
        broadcastAction(new GameStartAction());

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runUpdate();
            }
        }, 0, updateInterval);
    }

    public void stopGame() {
        timer.cancel();

        broadcastAction(new GameStopAction());

        for (Client c : clients) {
            c.removeGame();
        }
        GameSessionManager.getInstance().removeGame(key);
    }

    public void runUpdate() {
        GameStateUpdateAction updateStateaction = new GameStateUpdateAction();
        updateStateaction.addBody(paddle);
        broadcastAction(updateStateaction);
    }

}
