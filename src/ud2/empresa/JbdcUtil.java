package ud2.empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JbdcUtil {
    public static Connection getConnection() {
        String url = "jdbc:h2:mem:empresa;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";

        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }

        return conn;
    }
}
