package ud2.instituto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImplTran implements AlumnoDAO{
    private Connection conn;
    
    public AlumnoDAOImplTran(Connection conn) {
        this.conn = conn;
    }

    public List<AlumnoDTO> listarTodos() {
        List<AlumnoDTO> alumnos = null;
        
        String sql = "SELECT * FROM alumnos";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            alumnos = new ArrayList<>();
            while (rs.next()) {
                alumnos.add(new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("curso"),
                        rs.getDouble("nota_media")));
            }
            return alumnos;

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(AlumnoDTO alumno) {
        String sql = """
                INSERT INTO alumnos
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement sentenciaSql = conn.prepareStatement(sql)) {
            
            sentenciaSql.setInt(1, alumno.getId());
            sentenciaSql.setString(2, alumno.getNombre());
            sentenciaSql.setString(3, alumno.getApellidos());
            sentenciaSql.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSql.setString(5, alumno.getCurso());
            sentenciaSql.setDouble(6, alumno.getNotaMedia());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSql = conn.prepareStatement(sql)) {
            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(AlumnoDTO alumno) {
        
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSql = conn.prepareStatement(sql)){
            sentenciaSql.setInt(1, alumno.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AlumnoDTO buscarPorId(int id) {
        AlumnoDTO alumnoRes = null;
        
        String sql = """
                SELECT FROM alumnos
                WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSql = conn.prepareStatement(sql)){

            sentenciaSql.setInt(1, id);
            ResultSet rs = sentenciaSql.executeQuery();
            if (rs.next()) {
                alumnoRes = new AlumnoDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_nacimiento").toLocalDate(),
                        rs.getString("curso"),
                        rs.getDouble("nota_media"));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnoRes;
    }

    public boolean actualizar(AlumnoDTO alumno) {
        
        String sql = """
                UPDATE alumnos
                SET nombre = ?,
                    apellidos = ?,
                    fecha_nacimiento = ?,
                    curso = ?,
                    nota_media = ?
                    WHERE ID = ?
                """;
        try (PreparedStatement sentenciaSql = conn.prepareStatement(sql)){
            
            sentenciaSql.setString(1, alumno.getNombre());
            sentenciaSql.setString(2, alumno.getApellidos());
            sentenciaSql.setDate(3, Date.valueOf(alumno.getFechaNacimiento()));
            sentenciaSql.setString(4, alumno.getCurso());
            sentenciaSql.setDouble(5, alumno.getNotaMedia());
            sentenciaSql.setInt(6, alumno.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
