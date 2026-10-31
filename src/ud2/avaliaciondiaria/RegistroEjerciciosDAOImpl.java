package ud2.avaliaciondiaria;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ud2.clientes.JbdcUtil;

public class RegistroEjerciciosDAOImpl implements RegstroEjerciciosDAO {

    public List<RegistroEjerciciosDTO> listarTodos() {
        List<RegistroEjerciciosDTO> ejercicios = null;
        String sql = "SELECT * FROM registro_ejercicios";
        try (Connection con = JbdcUtil.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            ejercicios = new ArrayList<>();
            while (rs.next()) {
                ejercicios.add(new RegistroEjerciciosDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        LocalDate.parse(rs.getString("fecha")),
                        rs.getInt("id_alumno"),
                        rs.getInt("puntuacion")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los registros de ejercicios: " + e.getMessage());
        }

        return ejercicios;
    }

    public boolean insertar(RegistroEjerciciosDTO ejercicio) {
        String sql = "INSERT INTO registro_ejercicios (id, nombre, fecha, id_alumno, puntuacion) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, ejercicio.getId());
            sentenciaSql.setString(2, ejercicio.getNombre());
            sentenciaSql.setDate(3, Date.valueOf(ejercicio.getFecha()));
            sentenciaSql.setInt(4, ejercicio.getIdAlumno());
            sentenciaSql.setInt(5, ejercicio.getPuntuacion());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el registro: " + e.getMessage());
        }

        return false;
    }

    public boolean eliminar(RegistroEjerciciosDTO ejercicio) {
        String sql = "DELETE FROM registro_ejercicios WHERE id = ?";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, ejercicio.getId());
        } catch (SQLException e) {
            System.out.println("Error al borrar el registro: " + e.getMessage());
        }

        return false;
    }

    public boolean actualizar(RegistroEjerciciosDTO ejercicio) {
        String sql = "UPDATE registro_ejercicios (nombre, fecha, id_alumno, puntuacion) VALUES (?, ?, ?, ?) WHERE id = ?";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setString(1, ejercicio.getNombre());
            sentenciaSql.setDate(2, Date.valueOf(ejercicio.getFecha()));
            sentenciaSql.setInt(3, ejercicio.getIdAlumno());
            sentenciaSql.setInt(4, ejercicio.getPuntuacion());
            sentenciaSql.setInt(5, ejercicio.getId());
        } catch (SQLException e) {
            System.out.println("Error al actualizar el registro: " + e.getMessage());
        }
        return false;
    }

}
