package nu.smashit.core;

// @author Jonas
import nu.smashit.socket.Client;
import nu.smashit.core.Player.PlayerType;
import nu.smashit.socket.actions.GameStartAction;

public class SingleplayerGame extends Game {

    public SingleplayerGame(String key, Client c) {
        super(key);

        this.players = new Player[1];
        players[0] = new Player(c, PlayerType.PLAYER_1);

        c.setGame(this);
    }

    @Override
    public Player getPlayer(Client c) {
        return getPlayer();
    }

    public Player getPlayer() {
        return players[0];
    }

    @Override
    public void join(Client c) {
        throw new Error("Impossible to join a singleplayer game.");
    }

    @Override
    public void startGame() {
        broadcastAction(new GameStartAction());

        this.gameLoop = new SingleplayerLoop(this);

        gameLoopTimer.scheduleAtFixedRate(this.gameLoop, startDelay, updateInterval);
    }

}
