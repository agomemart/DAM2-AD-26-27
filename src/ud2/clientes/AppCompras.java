package ud2.clientes;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AppCompras {
    public static void main(String[] args) {
        CompraDAO compraDAO = new CompraDAOImpl();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("MENU COMPRAS");
            System.out.println("0. Crear Tabla/BD");
            System.out.println("1. Insertar");
            System.out.println("2. Listar");
            System.out.println("3. Buscar por ID");
            System.out.println("4. Actualizar");
            System.out.println("5. Eliminar");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 0:
                    crearTabla();
                    break;
                case 1:
                    System.out.print("Importe: ");
                    double importe = sc.nextDouble();
                    System.out.print("Fecha: ");
                    LocalDate fecha = LocalDate.parse(sc.nextLine().trim(), formato);
                    System.out.print("Id cliente: ");
                    int idCliente = sc.nextInt();
                    
                    CompraDTO c = new CompraDTO(idCliente, importe, fecha);
                    System.out.println(compraDAO.insertar(c)
                            ? "Insertado correctamente"
                            : "No se ha podido insertar");
                    break;
                case 2:
                    List<CompraDTO> lista = compraDAO.listarTodos();
                    if (lista == null || lista.isEmpty()) {
                        System.out.println("No hay compras");
                    } else {
                        System.out.println("Lista de compras:");
                        lista.forEach(System.out::println);
                    }
                    break;
                case 3:
                    System.out.print("Id de la compra a buscar: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    CompraDTO compra = compraDAO.buscarPorId(id);
                    System.out.println(compra != null ? compra : "Compra no encontrada");
                    break;
                case 4:
                    System.out.print("Actualizar la compra con id: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();
                    if (compraDAO.buscarPorId(id2) != null) {
                        System.out.print("Importe: ");
                    double importeActualizado = sc.nextDouble();
                    System.out.print("Fecha: ");
                    LocalDate fechaActualizada = LocalDate.parse(sc.nextLine().trim(), formato);
                    System.out.print("Id cliente: ");
                    int idClienteActualizado = sc.nextInt();
                    
                    CompraDTO compraActualizada = new CompraDTO(id2, idClienteActualizado, importeActualizado, fechaActualizada);
                        System.out.println(compraDAO.actualizar(compraActualizada)
                                ? "Actualizado correctamente"
                                : "No se ha podido actualizar");
                    } else {
                        System.out.println("No existe una compra con ese id");
                    }
                    break;
                case 5:
                    System.out.print("Inserta el id de la compra a borrar: ");
                    int id3 = sc.nextInt();
                    sc.nextLine();
                    if (compraDAO.eliminar(id3)) {
                        System.out.println("Eliminada correctamente");
                    } else {
                        System.out.println("No se ha podido eliminar la compra con id: " + id3);
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

    private static void crearTabla() {
        try (Connection conn = JbdcUtil.getConnection();
                Statement stmt = conn.createStatement()) {
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS compras(
                        id INT PRIMARY KEY AUTO_INCREMENT,
                        idCliente INT NOT NULL,
                        importe DECIMAL(10,2) NOT NULL,
                        fecha DATE NOT NULL,
                        CONSTRAINT fk_compras_cliente
                            FOREIGN KEY (idCliente) REFERENCES clientes(id)
                            ON DELETE CASCADE
                    )
                    """);
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return;
        }
    }
}
