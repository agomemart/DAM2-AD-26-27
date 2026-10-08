package ud2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ejemplo1JdbcH2 {
    public static void main(String[] args) {
        String url = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
        String user = "sa";
        String password = "";
        // Conexión con try-resources. Se cierra automáticamente
        try (Connection conn = DriverManager.getConnection(url, user, password);
                Statement stmt = conn.createStatement()) {
            // Crear una tabla de ejemplo
            stmt.execute("CREATE TABLE users(id INT PRIMARY KEY, name VARCHAR(50))");
            System.out.println("Tabla creada con éxito.");
            // Insertar datos
            stmt.execute("INSERT INTO users VALUES(1, 'Pepe')");
            stmt.execute("INSERT INTO users VALUES(2, 'Marta')");
            System.out.println("Datos insertados.");

            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("(" + rs.getInt("id") + ") " + rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
