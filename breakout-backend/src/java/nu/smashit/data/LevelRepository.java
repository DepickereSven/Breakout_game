package nu.smashit.data;

// @author Jonas

import nu.smashit.data.dataobjects.Difficulty;

public interface LevelRepository {

    public Difficulty getDifficulty(int level);
    
}
