package nu.smashit.data;

// @author Jonas

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nu.smashit.data.dataobjects.PowerData;
import nu.smashit.data.utils.MySqlConnection;
import nu.smashit.utils.BreakoutException;

public class MySqlPowerRepository implements PowerRepository{

    private static final String SQL_GET_POWER = "SELECT * FROM power WHERE powerID = ?";
    
    @Override
    public PowerData getPower(int id) {
        try (   Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_POWER); ) {

            prep.setInt(1, id);
            try(ResultSet rs = prep.executeQuery();){
                while (rs.next()) {
                    int powerID = rs.getInt("powerID");
                    String type = rs.getString("power_type");
                    double value = rs.getDouble("value");
                    return new PowerData(powerID, type, value);
                }
            }
            
            throw new BreakoutException("Could not get power data.");
        } catch (SQLException ex) {
            throw new BreakoutException("Could not get power data.", ex);
        }
    }
}