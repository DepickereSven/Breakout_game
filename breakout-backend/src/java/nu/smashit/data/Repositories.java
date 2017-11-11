package nu.smashit.data;

// @author Jonas
public class Repositories {

    public UserRepository getUserRepository() {
        return new MySqlUserRepository();
    }

    public BrickTypeRepository getBrickTypeRepository() {
        return new MySqlBrickTypeRepository();
    }

    public LevelRepository getLevelRepository() {
        return new MySqlLevelRepository();
    }

    public ScoreRepository getScoreRepository() {
        return new MySqlScoreRepository();
    }

}
