package nu.smashit.core;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class GameSessionManager {

    private static final GameSessionManager INSTANCE = new GameSessionManager();
    private final Map<String, GameSession> gameSessions;

    private GameSessionManager() {
        this.gameSessions = new HashMap<>();
    }

    public static GameSessionManager getInstance() {
        return INSTANCE;
    }

    public GameSession createGame(Client c) {
        String key = generateKey();
        GameSession gm = new GameSession(key);
        gm.join(c);
        gameSessions.put(gm.getKey(), gm);
        return gm;
    }

    public GameSession joinGame(String key, Client c) {
        GameSession gm = gameSessions.get(key);
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
