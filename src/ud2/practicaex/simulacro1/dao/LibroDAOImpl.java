package ud2.practicaex.simulacro1.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ud2.practicaex.simulacro1.dto.LibroConAutorDTO;
import ud2.practicaex.simulacro1.dto.LibroDTO;
import ud2.practicaex.simulacro1.util.JdbcUtil;

public class LibroDAOImpl implements LibroDAO {
    AutorDAO autorDAO; 

    public int insertar(LibroDTO libro) {
        String sql = "INSERT INTO libros (isbn, titulo, anio, precio, disponible, id_autor) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            sentenciaSql.setInt(1, libro.getIsbn());
            sentenciaSql.setString(2, libro.getTitulo());
            sentenciaSql.setInt(3, libro.getAnio());
            sentenciaSql.setDouble(4, libro.getPrecio());
            sentenciaSql.setBoolean(5, libro.isDisponible());
            sentenciaSql.setInt(6, libro.getIdAutor());
            sentenciaSql.executeQuery();

            ResultSet rs = sentenciaSql.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar el libro: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return 0;
    }

    public LibroDTO buscarPorId(int id) {
        if (id <= 0) {
            return null;
        }

        String sql = "SELECT (isbn, titulo, anio, precio, disponible, id_autor) FROM libros WHERE id = ?";
        LibroDTO libro = null;
        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql);
                ResultSet rs = sentenciaSql.executeQuery()) {

            if (rs.next()) {
                libro = new LibroDTO(
                        rs.getInt("isbn"),
                        rs.getString("titulo"),
                        rs.getInt("anio"),
                        rs.getDouble("precio"),
                        rs.getBoolean("disponible"),
                        rs.getInt("id_autor"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el libro con id " + id + ": " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return libro;
    }

    public List<LibroDTO> listarTodos() {
        List<LibroDTO> libros = new ArrayList<>();
        String sql = "SELECT (isbn, titulo, anio, precio, disponible, id_autor) FROM libros";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql);
                ResultSet rs = sentenciaSql.executeQuery()) {
            while (rs.next()) {
                libros.add(new LibroDTO(rs.getInt("isbn"),
                        rs.getString("titulo"),
                        rs.getInt("anio"),
                        rs.getDouble("precio"),
                        rs.getBoolean("disponible"),
                        rs.getInt("id_autor")));
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return libros;
    }

    public boolean actualizar(LibroDTO libro) {
        String sql = "UPDATE libros SET isbn = ?, titulo = ?, anio = ?, precio = ?, disponible = ?, id_autor = ? WHERE id = ?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, libro.getIsbn());
            sentenciaSql.setString(2, libro.getTitulo());
            sentenciaSql.setInt(3, libro.getAnio());
            sentenciaSql.setDouble(4, libro.getPrecio());
            sentenciaSql.setBoolean(5, libro.isDisponible());
            sentenciaSql.setInt(6, libro.getIdAutor());
            sentenciaSql.setInt(7, libro.getId());

            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Error de E/S");
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM libros WHERE id = ?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {
            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
            return false;
        } catch (IOException e) {
            System.out.println("Error de E/S");
            return false;
        }
    }

    public List<LibroConAutorDTO> listarConAutor() {
        List<LibroConAutorDTO> librosConAutor = new ArrayList<>();
        
        
        return librosConAutor;
    }

    public List<LibroDTO> buscarPorAutor(int autorId) {
        List<LibroDTO> libros = new ArrayList<>();
        String sql = "SELECT (id, isbn, titulo, anio, precio, disponible) WHERE id_autor = ?";

        try (Connection con = JdbcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql);
                ResultSet rs = sentenciaSql.executeQuery()) {

            libros.add(new LibroDTO(rs.getInt("id"),
                    rs.getInt("isbn"),
                    rs.getString("titulo"),
                    rs.getInt("anio"),
                    rs.getDouble("precio"),
                    rs.getBoolean("disponible")));

        } catch (SQLException e) {
            System.out.println("Error al listar los libros: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }
        return libros;
    }

}
