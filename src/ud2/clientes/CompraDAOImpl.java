package ud2.clientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO {
    public List<CompraDTO> listarTodos() {
        List<CompraDTO> compras = null;

        try (Connection conn = JbdcUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM compras")) {

            compras = new ArrayList<>();
            while (rs.next()) {
                compras.add(new CompraDTO(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getDouble("importe"),
                        rs.getDate("fecha").toLocalDate()));
            }

            return compras;

        } catch (SQLException e) {
            System.out.println("Error de sql: " + e.getMessage());
            return null;
        }
    }

    public boolean insertar(CompraDTO compra) {

        String sql = """
                INSERT INTO compras (idCliente, importe, fecha)
                VALUES (?, ?, ?)
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, compra.getIdCliente());
            sentenciaSql.setDouble(2, compra.getImporte());
            sentenciaSql.setDate(3, Date.valueOf(compra.getFecha()));
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error de sql en insertar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = """
                DELETE FROM compras
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, id);
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(CompraDTO compra) {
        String sql = """
                DELETE FROM compras
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, compra.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public CompraDTO buscarPorId(int id) {
        CompraDTO compraRes = null;

        String sql = """
                SELECT * FROM compras
                WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, id);
            ResultSet rs = sentenciaSql.executeQuery();
            if (rs.next()) {
                compraRes = new CompraDTO(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getDouble("importe"),
                        rs.getDate("fecha").toLocalDate());
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return compraRes;
    }

    public boolean actualizar(CompraDTO compra) {

        String sql = """
                UPDATE compras
                SET idCliente = ?,
                    importe = ?,
                    fecha = ?
                    WHERE ID = ?
                """;
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement sentenciaSql = con.prepareStatement(sql)) {

            sentenciaSql.setInt(1, compra.getIdCliente());
            sentenciaSql.setDouble(2, compra.getImporte());
            sentenciaSql.setDate(3, Date.valueOf(compra.getFecha()));
            sentenciaSql.setInt(5, compra.getId());
            return sentenciaSql.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void insertarCompras(List<CompraDTO> compras) {
        String sql = "INSERT INTO compras (idCliente, importe, fecha) VALUES (?, ?, ?)";
        try (Connection con = JbdcUtil.getConnection()) {
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                con.setAutoCommit(false);
                for (CompraDTO c : compras) {
                    ps.setInt(1, c.getIdCliente());
                    ps.setDouble(2, c.getImporte());
                    ps.setDate(3, Date.valueOf(c.getFecha()));
                    ps.executeUpdate();
                }
                con.commit();
            } catch (SQLException e) {
                System.out.println("Error de sql, se deshace todo: " + e.getMessage());
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CompraDTO> listarPorCliente(int idCliente) {
        List<CompraDTO> compras = new ArrayList<>();
        String sql = "SELECT * FROM compras WHERE idCliente = ?";
        try (Connection con = JbdcUtil.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    compras.add(new CompraDTO(
                            rs.getInt("id"),
                            rs.getInt("idCliente"),
                            rs.getDouble("importe"),
                            rs.getDate("fecha").toLocalDate()));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error de sql en listarPorCliente: " + e.getMessage());
        }
        return compras;
    }
}