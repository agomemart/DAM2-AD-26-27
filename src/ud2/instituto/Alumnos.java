package ud2.instituto;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Alumnos {
    public static List<AlumnoDTO> findAll() {
        List<AlumnoDTO> alumnos = null;
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

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
            rs.close();
            stmt.close();
            conn.close();
            return alumnos;

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }

    public static void findAllold() {
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

            while (rs.next()) {
                System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " - "
                        + rs.getString("curso") + " - " + rs.getDouble("nota_media"));
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }

    public static void findByCurso(String curso) {
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT * FROM alumnos");

            while (rs.next()) {
                if (rs.getString("curso").equals(curso)) {
                    System.out.println(rs.getString("nombre") + " " + rs.getString("apellidos") + " - "
                            + rs.getString("curso") + " - " + rs.getDouble("nota_media"));
                }
            }
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
        }
    }

    public static boolean insertar(AlumnoDTO alumno) {
        Connection con = ConexionBD.conectar();
        String sql = """
                INSERT INTO alumnos
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
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

    public static boolean borrar(int id) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean borrar(AlumnoDTO alumno) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, alumno.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static AlumnoDTO find(AlumnoDTO alumno) {
        AlumnoDTO alumnoRes = null;
        Connection con = ConexionBD.conectar();
        String sql = """
                SELECT FROM alumnos
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, alumno.getId());
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
            sentenciaSql.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumnoRes;
    }

    public static boolean actualizar(AlumnoDTO alumno) {
        Connection con = ConexionBD.conectar();
        String sql = """
                UPDATE alumnos
                SET nombre = ?,
                    apellidos = ?,
                    fecha_nacimiento = ?,
                    curso = ?,
                    nota_media = ?
                    WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);

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
