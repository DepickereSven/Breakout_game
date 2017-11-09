package nu.smashit.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jodus
 */
public class MySqlConnection {

    private static final String URL = "jdbc:mysql://server.thomasbilliet.com/breakout";
    private static final String UID = "usrbreakout";
    private static final String PWD = "Tibreakout2017";

    // loading the class is only required for web applications
    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            throw new Error("Unable to load database driver.", ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, UID, PWD);
    }

}
