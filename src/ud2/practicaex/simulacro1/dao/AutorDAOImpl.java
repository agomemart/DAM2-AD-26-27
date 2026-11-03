package ud2.practicaex.simulacro1.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ud2.practicaex.simulacro1.dto.AutorConNumLibrosDTO;
import ud2.practicaex.simulacro1.dto.AutorDTO;
import ud2.practicaex.simulacro1.util.JdbcUtil;

public class AutorDAOImpl implements AutorDAO {

    public int insertar(AutorDTO autor) {
        String sql = "INSERT INTO autores (nombre, nacionalidad) VALUES (?, ?)";
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            sentenciaSql.setString(1, autor.getNombre());
            sentenciaSql.setString(2, autor.getNacionalidad());
            sentenciaSql.executeQuery();

            ResultSet rs = sentenciaSql.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el autor: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return 0;
    }

    public AutorDTO buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }

        String sql = "SELECT (nombre, nacionalidad) FROM autores WHERE id = ?";
        AutorDTO autor = null;
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql);
                ResultSet rs = sentenciaSql.executeQuery()) {

            if (rs.next()) {
                autor = new AutorDTO(
                        rs.getString("nombre"),
                        rs.getString("nacionalidad"));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el autor con id " + id + ": " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return autor;
    }

    public List<AutorDTO> listarTodos() {
        List<AutorDTO> autores = new ArrayList<>();
        String sql = "SELECT (id, nombre, nacionalidad) FROM libros";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql);
                ResultSet rs = sentenciaSql.executeQuery()) {
            while (rs.next()) {
                autores.add(new AutorDTO(rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("nacionalidad")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return autores;
    }

    public boolean actualizar(AutorDTO autor) {
        String sql = "UPDATE autores SET nombre = ?, nacionalidad = ? WHERE id = ?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setString(1, autor.getNombre());
            sentenciaSql.setString(2, autor.getNacionalidad());
            sentenciaSql.setInt(3, autor.getId());

            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Error de E/S");
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM autores WHERE id = ?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al listar los autores: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Error de E/S");
            return false;
        }
    }

    @Override
    public List<AutorConNumLibrosDTO> listarConNumLibros() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarConNumLibros'");
    }

}
