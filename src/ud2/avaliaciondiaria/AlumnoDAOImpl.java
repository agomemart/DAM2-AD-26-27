package ud2.avaliaciondiaria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO{
    
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
                    rs.getString("curso")
                ));
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
    
}
