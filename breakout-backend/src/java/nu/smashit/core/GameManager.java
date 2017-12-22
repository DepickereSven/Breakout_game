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
    private final Map<String, Game> multiplayerPrivateGames;
    private final Queue<Game> multiplayerPublicGamesQueue;

    private GameManager() {
        this.multiplayerPrivateGames = new HashMap<>();
        this.multiplayerPublicGamesQueue = new PriorityBlockingQueue<>();
    }

    public static GameManager getInstance() {
        return INSTANCE;
    }

    public Game createSingleplayerGame(User u, int level) {
        String key = generateKey();
        return new SingleplayerGame(key, u, level);
    }

    public Game createMultiplayerGame(User u) {
        String key = generateKey();
        MultiplayerGame game = new MultiplayerGame(key);

        game.join(u);
        multiplayerPrivateGames.put(game.getKey(), game);
        return game;
    }

    public Game joinPrivateMultiplayerGame(String key, User u) {
        Game game = multiplayerPrivateGames.get(key);
        game.join(u);
        return game;
    }

    public Game joinPublicMultiplayerGame(User u) {
        Game game = multiplayerPublicGamesQueue.poll();
        if (game != null) {
            game.join(u);
        } else {
            game = createMultiplayerGame(u);
            multiplayerPublicGamesQueue.add(game);
        }
        return game;
    }

    public void removeGame(String key) {
        Game publicGame = getPublicGameByKey(key);
        multiplayerPublicGamesQueue.remove(publicGame);

        multiplayerPrivateGames.remove(key);
    }

    private Game getPublicGameByKey(String key) {
        return multiplayerPublicGamesQueue.stream().filter((o) -> o.getKey().equals(key)).findFirst().orElse(null);
    }

    private String generateKey() {
        String key = null;
        Game publicGame = null;

        do {
            key = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
            publicGame = getPublicGameByKey(key);
        }while (key == null || multiplayerPrivateGames.containsKey(key) || multiplayerPublicGamesQueue.contains(publicGame));
        
        return key;
    }
}
