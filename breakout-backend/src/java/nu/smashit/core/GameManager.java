package nu.smashit.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class GameManager {

    private static final GameManager INSTANCE = new GameManager();
    private final Map<String, Game> gameSessions;

    private GameManager() {
        this.gameSessions = new HashMap<>();
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public Game createMultiplayerGame(Client c) {
        String key = generateKey();
        MultiplayerGame gm = new MultiplayerGame(key);

        gm.join(c);
        gameSessions.put(gm.getKey(), gm);
        return gm;
    }
    
    public Game createSingleplayerGame(Client c){
        String key = generateKey();
        Game gm = new SingleplayerGame(key, c);
        
        gameSessions.put(gm.getKey(), gm);
        return gm;
    }
    
    public Game joinMultiplayerGame(String key, Client c) {
        Game gm = gameSessions.get(key);
        gm.join(c);
        return gm;
    }

    public void removeGame(String key) {
        gameSessions.remove(key);
    }

    private String generateKey() {
        String key = null;
        while (key == null || gameSessions.containsKey(key)) {
            key = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        }
        return key;
    }
}
