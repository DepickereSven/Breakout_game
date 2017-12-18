package nu.smashit.data;

// @author Jonas

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nu.smashit.data.dataobjects.Difficulty;
import nu.smashit.data.utils.MySqlConnection;
import nu.smashit.utils.BreakoutException;

public class MySqlLevelRepository implements LevelRepository{

    private static final String SQL_GET_DIFFICULTY_OF_LEVEL = 
            "SELECT difficulty.* FROM niveau INNER JOIN difficulty ON niveau.difficultyID = difficulty.difficultyID WHERE niveau.niveauID = ?";
    
    @Override
    public Difficulty getDifficulty(int level) {
        try(    Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_DIFFICULTY_OF_LEVEL);    ){
            
            prep.setInt(1, level);
            
            try(ResultSet rs = prep.executeQuery();){
                if (rs.next()){
                    int id = rs.getInt("difficultyID");
                    int numberPowerups = rs.getInt("number_powerups");
                    int numberPowerdowns = rs.getInt("number_powerdowns");
                    int rows = rs.getInt("rows");
                    double speedBall = rs.getInt("speed_ball");

                    return new Difficulty(id, numberPowerups, numberPowerdowns, rows, speedBall);
                }
                throw new BreakoutException("Could not get difficulty.");
            }

        } catch (SQLException ex) {
           throw new BreakoutException("Could not get difficulty.", ex);
        }
    }

}
