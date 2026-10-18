package ud2.empresa;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class AppEmpresa {

    public static void main(String[] args) {
        try (Connection conn = ConexionBD.conectar();
                Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS clientes");
            stmt.execute(
                    "CREATE TABLE clientes(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_alta DATE, vip BOOLEAN)");
        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return;
        }

        ClienteDTO cliente1 = new ClienteDTO(1, "Adrián", "Gómez", LocalDate.of(2025, 5, 25), true);
        ClienteDTO cliente2 = new ClienteDTO(2, "Manuel", "López", LocalDate.of(2024, 4, 28), false);
        ClienteDTO cliente3 = new ClienteDTO(3, "María", "Martínez", LocalDate.of(2020, 2, 14), false);
        ClienteDTO cliente4 = new ClienteDTO(4, "Marcos", "González", LocalDate.of(2023, 10, 2), true);
        ClienteDTO cliente5 = new ClienteDTO(5, "Laura", "Rey", LocalDate.of(2025, 1, 4), false);

        System.out.println("Insertar:");
        System.out.println("Insertar cliente 1: " + Clientes.insertar(cliente1));
        System.out.println("Insertar cliente 2: " + Clientes.insertar(cliente2));
        System.out.println("Insertar cliente 3: " + Clientes.insertar(cliente3));
        System.out.println("Insertar cliente 4: " + Clientes.insertar(cliente4));
        System.out.println("Insertar cliente 5: " + Clientes.insertar(cliente5));
        System.out.println("Insertar cliente 1 otra vez (id repetido): " + Clientes.insertar(cliente1));

        System.out.println("Imprimir todos los clientes:");
        Clientes.findAll().forEach(System.out::println);

        System.out.println("Imprimir un cliente en concreto:");
        System.out.println("find(ClienteDTO id 3): " + Clientes.find(new ClienteDTO(3)));
        System.out.println("find(id 99): " + Clientes.find(new ClienteDTO(99)));

        System.out.println("Actualizar cliente:");
        ClienteDTO modificado = new ClienteDTO(2, "Manuel", "López Pérez", LocalDate.of(2024, 5, 1), true);
        System.out.println("Actualizar cliente 2: " + Clientes.actualizar(modificado));
        System.out.println("Actualizar id 99: "
                + Clientes.actualizar(new ClienteDTO(99, "Nadie", "Nadie", LocalDate.now(), false)));

        System.out.println("Borrar por id:");
        System.out.println("Borrar id 5: " + Clientes.borrar(5));
        System.out.println("Borrar id 5 otra vez: " + Clientes.borrar(5));

        System.out.println("Borrar por cliente:");
        System.out.println("Borrar cliente 4: " + Clientes.borrar(cliente4));
        System.out.println("Borrar cliente 4 otra vez: " + Clientes.borrar(cliente4));

        System.out.println("Datos finales:");
        Clientes.findAll().forEach(System.out::println);
    }
}
