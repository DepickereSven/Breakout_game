package nu.smashit.data;

// @author Jonas

import java.util.List;
import nu.smashit.data.dataobjects.Score;

public interface ScoreRepository {

    public void addScore(Score score);
    public List<Score> getAllScores();
}
