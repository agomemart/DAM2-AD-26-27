package ud2.clientes;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDAOImpl implements CompraDAO{
    public List<CompraDTO> listarTodos() {
        List<CompraDTO> compras = null;

        try (Connection conn = JbdcUtil.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM clientes")) {

            compras = new ArrayList<>();
            while (rs.next()) {
                compras.add(new CompraDTO(
                        rs.getInt("id"),
                        rs.getInt("idCliente"),
                        rs.getDouble("importe"),
                        rs.getDate("fecha_alta").toLocalDate()));
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
                SELECT * FROM clientes
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
        try (Connection con = JbdcUtil.getConnection()) {
            try {
                con.setAutoCommit(false);

                String sql = """
                        INSERT INTO compras (idCliente, importe, fecha)
                        VALUES (?, ?, ?, ?)
                        """;
                PreparedStatement sentenciaSql = con.prepareStatement(sql);

                for (CompraDTO c : compras) {
                    sentenciaSql.setInt(1, c.getIdCliente());
                    sentenciaSql.setDouble(2, c.getImporte());
                    sentenciaSql.setDate(3, Date.valueOf(c.getFecha()));
                    sentenciaSql.setInt(5, c.getId());
                    sentenciaSql.executeUpdate();
                }

                con.commit();

            } catch (SQLException e) {
                e.printStackTrace();
                con.rollback();
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}