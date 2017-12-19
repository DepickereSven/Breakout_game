package nu.smashit.socket.actions;

// @author Jonas

import java.util.List;
import nu.smashit.data.Repositories;
import nu.smashit.data.dataobjects.Score;

public class ScoresResponseAction implements ResponseAction{

    public List<Score> scores;
    
    public ScoresResponseAction(){
        this.scores = Repositories.getScoreRepository().getAllScores();
    }
    
}
