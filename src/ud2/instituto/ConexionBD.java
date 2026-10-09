package ud2.instituto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static Connection conectar() {
        String url = "jdbc:h2:mem:instituto;DB_CLOSE_DELAY=-1";
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
