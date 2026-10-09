package ud2.instituto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Alumnos {
    public static void findAll() {
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " - "
                        + rs.getString("curso") + " - " + rs.getDouble("nota_media"));
            }
            rs.close();
            conn.close();
                    
        }catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }

    public static void findByCurso(String curso) {
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

            while (rs.next()) {
                if (rs.getString("curso").equals(curso)) {
                    System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " - "
                        + rs.getString("curso") + " - " + rs.getDouble("nota_media"));
                }
            }
            rs.close();
            conn.close();
                    
        }catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }
}
