package nu.smashit.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.PriorityBlockingQueue;
import nu.smashit.data.dataobjects.User;

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

    public Game createMultiplayerGame(User u) {
        String key = generateKey();
        MultiplayerGame gm = new MultiplayerGame(key);

        gm.join(u);
        gameSessions.put(gm.getKey(), gm);
        return gm;
    }

    public Game createSingleplayerGame(User u, int level) {
        String key = generateKey();
        Game gm = new SingleplayerGame(key, u, level);

        gameSessions.put(gm.getKey(), gm);
        return gm;
    }

    public Game joinPrivateMultiplayerGame(String key, User u) {
        Game gm = gameSessions.get(key);
        gm.join(u);
        return gm;
    }

    public Game joinPublicMultiplayerGame(User u) {
        Game gm = publicGameSessionQueue.poll();
        if (gm != null) {
            gm.join(u);
        } else {
            gm = createMultiplayerGame(u);
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
