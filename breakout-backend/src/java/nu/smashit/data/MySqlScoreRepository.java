package nu.smashit.data;

// @author Jonas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nu.smashit.data.dataobjects.Score;
import nu.smashit.data.dataobjects.User;
import nu.smashit.data.utils.MySqlConnection;
import nu.smashit.utils.BreakoutException;

public class MySqlScoreRepository implements ScoreRepository {

    private static final String SQL_GET_ALL_SCORES = "SELECT * FROM scores";
    private static final String SQL_ADD_SCORE = "INSERT INTO scores (userWonID, userLostID, points, time) VALUES(?,?,?,?)";

    @Override
    public void addScore(Score score) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_ADD_SCORE);) {

            prep.setString(1, score.getUserWon().getUserID());
            prep.setString(2, score.getUserLost().getUserID());
            prep.setInt(3, score.getPoints());
            prep.setInt(4, score.getTime());
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new BreakoutException("Could not add score.", ex);
        }
    }

    @Override
    public List<Score> getAllScores() {
        try (   Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_ALL_SCORES);
                ResultSet rs = prep.executeQuery();) {

            List<Score> scores = new ArrayList();
            while (rs.next()) {
                int scoreID = rs.getInt("scoreID");
                String userWonID = rs.getString("userWonID");
                User userWon = Repositories.getUserRepository().getUser(userWonID).build();
                String userLostID = rs.getString("userLostID");
                User userLost = Repositories.getUserRepository().getUser(userLostID).build();
                int points = rs.getInt("points");
                int time = rs.getInt("time");
                scores.add(new Score(scoreID, userWon, userLost, points, time));
            }
            return scores;

        } catch (SQLException ex) {
            throw new BreakoutException("Could not get scores.", ex);
        }
    }

}
