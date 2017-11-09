package nu.smashit.core;

// @author Jonas

import nu.smashit.core.Player.PlayerType;
import nu.smashit.socket.Client;
import nu.smashit.socket.actions.GameStartAction;

public class MultiplayerSession extends GameSession{

    public MultiplayerSession(String key) {
        super(key);
        super.players = new Player[2];
    }
    
    @Override
    public void join(Client c) {
        if (players[0] == null){
            players[0] = new Player(c, PlayerType.PLAYER_1);
        }else if (players[1] == null){
            players[1] = new Player(c, PlayerType.PLAYER_2);
        }else{
            throw new Error("GameSession full");
        }

        c.setGame(this);
    }
    
    @Override
    public Player getPlayer(Client c) {
        return players[0].client == c ? players[0] : players[1];
    }
    
    public Player getTopPlayer() {
        return players[1];
    }

    public Player getBottomPlayer() {
        return players[0];
    }
    
    @Override
    public void startGame() {
        broadcastAction(new GameStartAction());

        this.gameLoop = new MultiplayerLoop(this);

        gameLoopTimer.scheduleAtFixedRate(this.gameLoop, 0, updateInterval);
    }

}