package ud2.instituto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JbdcUtil {
    public static Connection getConnection() {
        final String URL = "jdbc:h2:mem:instituto;DB_CLOSE_DELAY=-1";
        final String USER = "sa";
        final String PASS = "";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }

        return conn;
    }
}
