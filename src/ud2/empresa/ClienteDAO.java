package ud2.empresa;

import java.util.List;

public interface ClienteDAO {
    public List<ClienteDTO> listarTodos();

    public boolean insertar(ClienteDTO cliente);

    public boolean eliminar(int id);

    public boolean eliminar(ClienteDTO cliente);

    public ClienteDTO buscarPorId(int id);

    public boolean actualizar(ClienteDTO cliente);
}
