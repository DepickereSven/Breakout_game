package nu.smashit.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;
import nu.smashit.socket.Client;

/**
 *
 * @author jodus
 */
public class GameManager {

    private static final GameManager INSTANCE = new GameManager();
    private final Map<String, Game> gameSessions;
    private final Queue<Game> publicGameSessionQueue;

    private GameManager() {
        this.gameSessions = new HashMap<>();
        this.publicGameSessionQueue = new PriorityBlockingQueue<>();
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

    public Game createSingleplayerGame(Client c, int level) {
        String key = generateKey();
        Game gm = new SingleplayerGame(key, c, level);

        gameSessions.put(gm.getKey(), gm);
        return gm;
    }

    public Game joinPrivateMultiplayerGame(String key, Client c) {
        Game gm = gameSessions.get(key);
        gm.join(c);
        return gm;
    }

    public Game joinPublicMultiplayerGame(Client c) {
        Game gm = publicGameSessionQueue.poll();
        if (gm != null) {
            gm.join(c);
        } else {
            gm = createMultiplayerGame(c);
            publicGameSessionQueue.add(gm);
        }
        return gm;
    }

    public void removeGame(String key) {
        Game gm = gameSessions.get(key);
        if (!gm.isFull()) {
            publicGameSessionQueue.remove(gm);
        }
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
