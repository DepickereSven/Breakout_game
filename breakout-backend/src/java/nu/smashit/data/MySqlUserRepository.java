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

    private static final String SQL_ADD_USER = "INSERT INTO users (email, smashbit, username, image_url, country) VALUES (?,?,?,?,?)";
    private static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_GET_USER_BY_USERID = "SELECT * FROM users WHERE userID = ?";

    @Override
    public void addUser(User user) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_ADD_USER);) {

            prep.setString(1, user.getEmail());
            prep.setInt(2, user.getSmashbit());
            prep.setString(3, user.getUsername());
            prep.setString(4, user.getImageUrl());
            prep.setString(5, user.getCountry());
            prep.executeUpdate();

        } catch (SQLException ex) {
            throw new BreakoutException("Could not add user.", ex);
        }
    }

    @Override
    public User getUser(String email) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_USER_BY_EMAIL);) {

            prep.setString(1, email);
            ResultSet rs = prep.executeQuery();
            return createUserFromResultSet(rs);

        } catch (SQLException ex) {
            throw new BreakoutException("Could not get user by email.", ex);
        }
    }

    @Override
    public User getUser(int userID) {
        try (Connection conn = MySqlConnection.getConnection();
                PreparedStatement prep = conn.prepareStatement(SQL_GET_USER_BY_EMAIL);) {

            prep.setInt(1, userID);
            ResultSet rs = prep.executeQuery();
            return createUserFromResultSet(rs);

        } catch (SQLException ex) {
            throw new BreakoutException("Could not get user by userID.", ex);
        }
    }

    private User createUserFromResultSet(ResultSet rs) {
        try {
            if (rs.next()){
                String userID = Integer.toString(rs.getInt("userID"));
                String email = rs.getString("email");
                int smashbit = rs.getInt("smashbit");
                String username = rs.getString("username");
                String imageUrl = rs.getString("imageUrl");
                String country = rs.getString("country");
                return new User(userID, email, smashbit, username, imageUrl, country);
            }
            throw new BreakoutException("Could not create the user.");
        } catch (SQLException ex) {
            throw new BreakoutException("Could not create the user.", ex);
        }
    }

}
