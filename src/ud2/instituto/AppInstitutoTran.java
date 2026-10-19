package ud2.instituto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class AppInstitutoTran {
    public static void main(String[] args) {

        try (Connection conn = JbdcUtil.getConnection();
            Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE alumnos(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_nacimiento DATE, curso VARCHAR(100), nota_media DECIMAL(4,2))";
            stmt.execute(sql);

            stmt.executeUpdate("INSERT INTO alumnos VALUES(1, 'Adrián', 'Gómez', '2004-01-28', '2DAM', 8.5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(2, 'Pepe', 'Pérez', '2002-05-25', '2DAM', 9)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(3, 'María', 'Campos', '2008-09-01', '2DAM', 5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(4, 'Francisco', 'Martínez', '2006-08-06', '1ASIR', 6.5)");
            stmt.executeUpdate("INSERT INTO alumnos VALUES(5, 'Jorge', 'Rodríguez', '2003-12-09', '2ASIR', 7.8)");

            AlumnoDAOImplTran alumnoDAOImpl2 = new AlumnoDAOImplTran(conn);
            
            System.out.println("Mostrando alumnos:");
            alumnoDAOImpl2.listarTodos();
            System.out.println("Filtrar alumnos por curso:");

            AlumnoDTO alumno = new AlumnoDTO(6, "Manuel", "López", LocalDate.of(2000, 1, 1), "COM1", 7.);
            
            if (alumnoDAOImpl2.insertar(alumno)) {
                System.out.println("Insertado correctamente");
            } else {
                System.out.println("Error al insertr el alumno: " + alumno);
            }

            List<AlumnoDTO> alumnos = alumnoDAOImpl2.listarTodos();
            alumnos.forEach(System.out::println);

            Scanner sc = new Scanner(System.in);
            System.out.print("Id de usuario a borrar: ");
            int id = sc.nextInt();
            sc.close();
            alumnoDAOImpl2.eliminar(id);
            
            AlumnoDTO alumno2 = alumnoDAOImpl2.buscarPorId(id);
            if (alumno2 != null) {
                System.out.println(alumno2);
            } else {
                System.out.println("Error al recuperar el alumno " + id);
            }
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }
}
