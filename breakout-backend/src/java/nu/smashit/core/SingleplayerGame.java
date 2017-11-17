package nu.smashit.core;

// @author Jonas
import nu.smashit.socket.Client;
import nu.smashit.core.Player.PlayerType;

public class SingleplayerGame extends Game {

    public SingleplayerGame(String key, Client c, int level) {
        super(key, level);
        super.setPlayers( new Player[1]);
        super.getPlayers()[0] = new Player(c, PlayerType.PLAYER_1);
        c.setGame(this);
    }

    @Override
    public Player getPlayer(Client c) {
        return getPlayer();
    }

    public Player getPlayer() {
        return getPlayers()[0];
    }
    
    @Override
    protected void createGameLoop() {
        setGameLoop(new SingleplayerLoop(this, getLevel()));
    }

    @Override
    public void join(Client c) {
        throw new Error("Impossible to join a singleplayer game.");
    }

}
