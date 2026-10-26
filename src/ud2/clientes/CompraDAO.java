package ud2.clientes;

import java.util.List;

public interface CompraDAO {
    public List<CompraDTO> listarTodos();

    public boolean insertar(CompraDTO cliente);

    public boolean eliminar(int id);

    public boolean eliminar(CompraDTO cliente);

    public CompraDTO buscarPorId(int id);

    public boolean actualizar(CompraDTO cliente);

    public void insertarCompras(List<CompraDTO> clientes);

    public List<CompraDTO> listarPorCliente(int idCliente);

    public CompraClienteDTO buscarClientePorCompra(int idCompra);
}
