package ud2.empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AppEmpresa {

    public static void main(String[] args) {
        Connection conn = JbdcUtil.getConnection();
        ClienteDAO clienteDAO = new ClienteDAOImpl();

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENU ALUMNOS");
            System.out.println("0. Crear Tabla/BD");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción:");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    try (Statement stmt = conn.createStatement()) {
                        stmt.execute("DROP TABLE IF EXISTS clientes");
                        stmt.execute(
                                "CREATE TABLE clientes(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_alta DATE, vip BOOLEAN)");
                    } catch (SQLException e) {
                        System.out.println("Error de sql: " + e.getMessage());
                        return;
                    }
                    break;
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Apellidos: ");
                    String apellidos = sc.nextLine();
                    System.out.print("Fecha de alta (dd/mm/yyyy): ");
                    String fechaAlta = sc.nextLine();
                    System.out.print("Vip? (S/N): ");
                    String vip = sc.nextLine();
                    boolean esVip = vip.equalsIgnoreCase("s");
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha = LocalDate.parse(fechaAlta.trim(), formato);

                    ClienteDTO c = new ClienteDTO(nombre, apellidos, fecha, esVip);
                    clienteDAO.insertar(c);
                    break;
                case 2:
                    clienteDAO.listarTodos();
                    break;
                case 3:
                    System.out.print("Inserta el id del cliente a buscar: ");
                    int id = sc.nextInt();
                    if (clienteDAO.buscarPorId(id) == null) {
                        System.out.println("Cliente no encontrado");
                    }
                    break;
                case 4:
                    System.out.print("Actualizar el cliente con id: ");
                    int id2 = sc.nextInt();
                    if (clienteDAO.buscarPorId(id2) != null) {
                        System.out.print("Nombre: ");
                        String nombreActualizado = sc.nextLine();
                        System.out.print("Apellidos: ");
                        String apellidosActualizado = sc.nextLine();
                        System.out.print("Fecha de alta (dd/mm/yyyy): ");
                        String fechaAltaActualizado = sc.nextLine();
                        System.out.print("Vip? (S/N)");
                        String vipActualizado = sc.nextLine();
                        boolean esVipActualizado = false;
                        if (vipActualizado.equalsIgnoreCase("s")) {
                            esVip = true;
                        } else if (vipActualizado.equalsIgnoreCase("n")) {
                            esVip = false;
                        }
                        String[] partesFechaActualizado = fechaAltaActualizado.split("/");
                        ClienteDTO cActualizado = new ClienteDTO(nombreActualizado, apellidosActualizado,
                                LocalDate.of(Integer.valueOf(partesFechaActualizado[2]),
                                        Integer.valueOf(partesFechaActualizado[1]),
                                        Integer.valueOf(partesFechaActualizado[0])),
                                esVipActualizado);
                        clienteDAO.actualizar(cActualizado);
                    } else {
                        System.out.println("No existe un cliente con ese id");
                    }
                    break;
                case 5:
                    System.out.print("Inserta el id del cliente a borrar: ");
                    int id3 = sc.nextInt();
                    if (clienteDAO.eliminar(id3)) {
                        System.out.println("Eliminado correctamente");
                    } else {
                        System.out.println("No se ha podido eliminar el cliente con id: " + id3);
                    }
                    break;
                case 6:
                    sc.close();
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        } while (true);
    }
}
