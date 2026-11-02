package ud2.avaliaciondiaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    public List<AlumnoDTO> listarTodos() {
        List<AlumnoDTO> alumnos = null;
        String sql = "SELECT * FROM alumnos";

        try (Connection con = JbdcUtil.getConnection();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            alumnos = new ArrayList<>();
            while (rs.next()) {
                alumnos.add(new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("curso")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los alumnos: " + e.getMessage());
        }

        return alumnos;
    }

    public boolean insertar(AlumnoDTO alumno) {
        String sql = "INSERT INTO alumnos (id, nombre, apellidos, curso) VALUES (?, ?, ?, ?)";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, alumno.getId());
            sentenciaSql.setString(2, alumno.getNombre());
            sentenciaSql.setString(3, alumno.getApellidos());
            sentenciaSql.setString(4, alumno.getCurso());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el alumno: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(AlumnoDTO alumno) {
        String sql = "DELETE FROM alumnos WHERE id = ?";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, alumno.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
            return false;
        }

    }

    public boolean actualizar(AlumnoDTO alumno) {
        String sql = "UPDATE alumnos SET nombre = ?, apellidos = ?, curso = ? WHERE id = ?";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setString(1, alumno.getNombre());
            sentenciaSql.setString(2, alumno.getApellidos());
            sentenciaSql.setString(3, alumno.getCurso());
            sentenciaSql.setInt(4, alumno.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el alumno: " + e.getMessage());
            return false;
        }
    }

    public AlumnoDTO buscarPorId(int id) {
        AlumnoDTO alumnoDTO = null;
        String sql = "SELECT (nombre, apellidos, curso FROM alumnos WHERE id = ?)";

        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, id);

            ResultSet rs = sentenciaSql.executeQuery();
            if (rs.next()) {
                alumnoDTO = new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("curso"));
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al buscar el alumno: " + e.getMessage());
        }
        return alumnoDTO;
    }

    public AlumnoDTO sortear() {
        List<AlumnoDTO> alumnos = listarTodos();
        Collections.shuffle(alumnos);

        return alumnos.get(0);
    }

    public List<AlumnoDTO> buscarAlumnosPorApellido(String apellido) {
        String sql= "SELECT (id, nombre, curso) FROM alumnos WHERE apellido = ?";
        List<AlumnoDTO> alumnosPorApellido = null;
        
        try (Connection con = JbdcUtil.getConnection();
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            ResultSet rs = sentenciaSql.executeQuery()) {
                sentenciaSql.setString(1, apellido);
                alumnosPorApellido = new ArrayList<>();
            while (rs.next()) {
                alumnosPorApellido.add(new AlumnoDTO(rs.getInt("id"), rs.getString("nombre"), apellido, rs.getString("curso")));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar los alumnos por apellido: " + e.getMessage());
        }
        return alumnosPorApellido;
    }
}
