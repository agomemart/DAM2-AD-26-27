package ud2.clientes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * @author Adrián Gómez
 */
public class JbdcUtil {
    public static Connection getConnection() {
        String url = "jdbc:sqlite:./BD/empresa";
        String user = "sa";
        String password = "";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            try (Statement st = conn.createStatement()){
                st.execute("PRAGMA foreign_keys = ON");
            }
            return conn;
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }
}
