package ud2.avaliaciondiaria;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class AppAvaliacionDiaria {
    public static void main(String[] args) {
        AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENU Avaliación Diaria");
            System.out.println("0. Crear Tabla/BD");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Filtrar por nombre");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    crearTabla();
                    break;
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Curso: ");
                    String curso = sc.nextLine().trim();
                   
                    AlumnoDTO a = new AlumnoDTO(nombre, apellidos, curso);
                    System.out.println(alumnoDAO.insertar(a)
                                ? "Insertado correctamente"
                                : "No se ha podido insertar");
                    break;
                case 2:
                    List<AlumnoDTO> lista = alumnoDAO.listarTodos();
                    if (lista == null || lista.isEmpty()) {
                        System.out.println("No hay alumnos");
                    } else {
                        System.out.println("Lista de alumnos:");
                        lista.forEach(System.out::println);
                    }
                    break;
                case 3:
                    
                    break;
                case 4:
                    System.out.print("Actualizar el alumno con id: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    if (alumnoDAO.buscarPorId(id2) != null) {
                        System.out.print("Nombre: ");
                        String nombreActualizado = sc.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidosActualizado = sc.nextLine();
                        System.out.print("Curso");
                        String cursoActualizado = sc.nextLine();
                        
                        
                        AlumnoDTO aActualizado = new AlumnoDTO(nombreActualizado, apellidosActualizado, cursoActualizado);
                        System.out.println(alumnoDAO.actualizar(aActualizado)
                                ? "Actualizado correctamente"
                                : "No se ha podido actualizar");
                    } else {
                        System.out.println("No existe un cliente con ese id");
                    }
                    break;
                case 5:
                    System.out.print("Inserta el id del alumno a borrar: ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    if (alumnoDAO.eliminar(null)) {
                        System.out.println("Eliminado correctamente");
                    } else {
                        System.out.println("No se ha podido eliminar el alumno con id: " + id3);
                    }
                    break;
                case 6:
                    
                    break;
                case 7:
                    sc.close();
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        } while (true);
    }

    public static void crearTabla() {
        String sql = """
        CREATE TABLE IF NOT EXISTS alumnos (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50) NOT NULL,
            apellidos VARCHAR(100) NOT NULL,
            curso VARCHAR(50)
        );

        CREATE TABLE IF NOT EXISTS registro_ejercicios (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre VARCHAR(50) NOT NULL,
            fecha DATE NOT NULL,
            id_alumno INTEGER NOT NULL,
            puntuacion INTEGER CHECK (puntuacion BETWEEN 0 AND 5),
        FOREIGN KEY id_alumno REFERENCES alumno(id)
        );""";

        try (Connection con = JbdcUtil.getConnection();
            Statement stmt = con.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error al crear las tablas: " + e.getMessage());
        }
    }
}
