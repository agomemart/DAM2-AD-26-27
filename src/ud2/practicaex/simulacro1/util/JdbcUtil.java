package ud2.practicaex.simulacro1.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
    private static final String CONFIG_FILE = "./BD/config.properties";

    public static Connection getConnection() throws IOException, SQLException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        }
        String url = props.getProperty("db.url");
        String user = props.getProperty("db.user");
        String pass = props.getProperty("db.password", "");
        return DriverManager.getConnection(url, user, pass);
    }

    public static void ejecutarStript(String fichero, Connection con) {
        try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
            String sql = in.readAllAsString();
            try (Statement stmt = con.createStatement()) {
                stmt.execute(sql);
            } catch (SQLException e) {
                System.out.println("Error de SQL: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichero sql no encontrado");
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
    }
}
