package nu.smashit.data;

// @author Jonas
public class Repositories {

    public static UserRepository getUserRepository() {
        return new MySqlUserRepository();
    }

    public static BrickTypeRepository getBrickTypeRepository() {
        return new MySqlBrickTypeRepository();
    }

    public static LevelRepository getLevelRepository() {
        return new MySqlLevelRepository();
    }

    public static ScoreRepository getScoreRepository() {
        return new MySqlScoreRepository();
    }
    
    public static PowerRepository getPowerRepository(){
        return  new MySqlPowerRepository();
    }

}