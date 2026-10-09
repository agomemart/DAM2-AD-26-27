package ud2.instituto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppInstituto {
    public static void main(String[] args) {
        
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE alumnos(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_nacimiento DATE, curso VARCHAR(100), nota_media DECIMAL(4,2))";
            stmt.execute(sql);

            stmt.executeUpdate("INSERT INTO alumnos VALUES(1, 'Adrián', 'Gómez', '2004-01-28', '2DAM', 8.5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(2, 'Pepe', 'Pérez', '2002-05-25', '2DAM', 9)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(3, 'María', 'Campos', '2008-09-01', '2DAM', 5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(4, 'Francisco', 'Martínez', '2006-08-06', '1ASIR', 6.5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(5, 'Jorge', 'Rodríguez', '2003-12-09', '2ASIR', 7.8)");

            System.out.println("Mostrando alumnos:");
            Alumnos.findAll();
            System.out.println("Filtrar alumnos por curso:");
            Alumnos.findByCurso("2DAM");
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }
}
