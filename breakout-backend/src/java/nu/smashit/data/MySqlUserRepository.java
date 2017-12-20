package nu.smashit.data;

// @author Jonas
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nu.smashit.data.dataobjects.User;
import nu.smashit.data.utils.MySqlConnection;
import nu.smashit.utils.BreakoutException;

public class MySqlUserRepository implements UserRepository {

    private static final String SQL_ADD_USER = "INSERT INTO users (email, smashbit, username, image_url, country, userID) VALUES (?,?,?,?,?,?)";
    private static final String SQL_GET_USER_BY_USERID = "SELECT * FROM users WHERE userID = ?";
    private static final String SQL_UPDATE_SMASHBIT = "UPDATE users SET smashbit = ? WHERE userID = ?";

    @Override
    public void addUser(User user) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_ADD_USER);) {

            prep.setString(1, user.getEmail());
            prep.setInt(2, user.getSmashbit());
            prep.setString(3, user.getUsername());
            prep.setString(4, user.getImageUrl());
            prep.setString(5, user.getCountry());
            prep.setString(6, user.getUserID());
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new BreakoutException("Could not add user.", ex);
        }
    }

    @Override
    public User.Builder getUser(String userID) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_USER_BY_USERID);) {

            prep.setString(1, userID);

            try (ResultSet rs = prep.executeQuery();) {
                return createUserFromResultSet(rs);
            }

        } catch (SQLException ex) {
            throw new BreakoutException("Could not get user by userID.", ex);
        }
    }

    private User.Builder createUserFromResultSet(ResultSet rs) {
        try {
            if (rs.next()) {
                String userID = rs.getString("userID");
                String email = rs.getString("email");
                int smashbit = rs.getInt("smashbit");
                String username = rs.getString("username");
                String imageUrl = rs.getString("image_url");
                String country = rs.getString("country");

                return User.builder()
                        .setUserData(userID, email, smashbit, username, imageUrl, country);
            }
            throw new BreakoutException("Could not create the user.");
        } catch (SQLException ex) {
            throw new BreakoutException("Could not create the user.", ex);
        }
    }

    @Override
    public void updateSmashbit(User user) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_UPDATE_SMASHBIT);) {

            prep.setInt(1, user.getSmashbit());
            prep.setString(2, user.getUserID());
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new BreakoutException("Could not update users smashbit.", ex);
        }
    }

}