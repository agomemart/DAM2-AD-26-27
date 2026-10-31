package ud2.avaliaciondiaria;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AppAvaliacionDiaria {
    

    public void crearTabla() {
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
