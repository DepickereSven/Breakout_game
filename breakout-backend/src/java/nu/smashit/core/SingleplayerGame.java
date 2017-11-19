package nu.smashit.core;

// @author Jonas
import nu.smashit.core.Player.PlayerType;
import nu.smashit.data.dataobjects.User;

public class SingleplayerGame extends Game {

    public SingleplayerGame(String key, User u, int level) {
        super(key, level);
        super.setPlayers( new Player[1]);
        super.getPlayers()[0] = new Player(u, PlayerType.PLAYER_1);
        u.setGame(this);
    }

    @Override
    public Player getPlayer(User u) {
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
    public void join(User u) {
        throw new Error("Impossible to join a singleplayer game.");
    }

}
