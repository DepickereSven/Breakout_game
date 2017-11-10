package nu.smashit.data;

// @author Jonas

import nu.smashit.core.Difficulty;

public interface LevelRepository {

    public Difficulty getDiffuculty(int level);
    
}
