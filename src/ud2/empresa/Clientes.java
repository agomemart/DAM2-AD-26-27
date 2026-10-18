package ud2.empresa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class Clientes {
    public static List<ClienteDTO> findAll() {
        List<ClienteDTO> clientes = null;
        Connection conn = ConexionBD.conectar();

        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");

            clientes = new ArrayList<>();
            while (rs.next()) {
                clientes.add(new ClienteDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_alta").toLocalDate(),
                        rs.getBoolean("vip")));
            }
            rs.close();
            stmt.close();
            conn.close();
            return clientes;

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }

    public static boolean insertar(ClienteDTO cliente) {
        Connection con = ConexionBD.conectar();
        String sql = """
                INSERT INTO clientes (id, nombre, apellidos, fecha_alta, vip) 
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, cliente.getId());
            sentenciaSql.setString(2, cliente.getNombre());
            sentenciaSql.setString(3, cliente.getApellidos());
            sentenciaSql.setDate(4, Date.valueOf(cliente.getFechaAlta()));
            sentenciaSql.setBoolean(5, cliente.isVip());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error de sql en insertar: " + e.getMessage());
            return false;
        }
    }

    public static boolean borrar(int id) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM clientes
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

    public static boolean borrar(ClienteDTO cliente) {
        Connection con = ConexionBD.conectar();
        String sql = """
                DELETE FROM clientes
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, cliente.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ClienteDTO find(ClienteDTO cliente) {
        ClienteDTO clienteRes = null;
        Connection con = ConexionBD.conectar();
        String sql = """
                SELECT * FROM clientes
                WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);
            sentenciaSql.setInt(1, cliente.getId());
            ResultSet rs = sentenciaSql.executeQuery();
            if (rs.next()) {
                clienteRes = new ClienteDTO(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getDate("fecha_alta").toLocalDate(),
                        rs.getBoolean("vip"));
            }
            rs.close();
            sentenciaSql.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clienteRes;
    }

    public static boolean actualizar(ClienteDTO cliente) {
        Connection con = ConexionBD.conectar();
        String sql = """
                UPDATE clientes
                SET nombre = ?,
                    apellidos = ?,
                    fecha_alta = ?,
                    vip = ?
                    WHERE ID = ?
                """;
        try {
            PreparedStatement sentenciaSql = con.prepareStatement(sql);

            sentenciaSql.setString(1, cliente.getNombre());
            sentenciaSql.setString(2, cliente.getApellidos());
            sentenciaSql.setDate(3, Date.valueOf(cliente.getFechaAlta()));
            sentenciaSql.setBoolean(4, cliente.isVip());
            sentenciaSql.setInt(5, cliente.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
