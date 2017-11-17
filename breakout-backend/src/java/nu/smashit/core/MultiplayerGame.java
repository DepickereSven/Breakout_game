package nu.smashit.core;

// @author Jonas
import nu.smashit.core.Player.PlayerType;
import nu.smashit.socket.Client;

public class MultiplayerGame extends Game {

    public MultiplayerGame(String key) {
        super(key, 2);
        super.setPlayers( new Player[2]);
    }

    @Override
    public void join(Client c) {
        if (getPlayers()[0] == null) {
            getPlayers()[0] = new Player(c, PlayerType.PLAYER_1);
        } else if (getPlayers()[1] == null) {
            getPlayers()[1] = new Player(c, PlayerType.PLAYER_2);
        } else {
            throw new Error("GameSession full");
        }

        c.setGame(this);
    }

    @Override
    protected void createGameLoop() {
        setGameLoop(new MultiplayerLoop(this));
    }

    @Override
    public Player getPlayer(Client c) {
        return getPlayers()[0].getClient() == c ? getPlayers()[0] : getPlayers()[1];
    }

    public Player getTopPlayer() {
        return getPlayers()[1];
    }

    public Player getBottomPlayer() {
        return getPlayers()[0];
    }
}
