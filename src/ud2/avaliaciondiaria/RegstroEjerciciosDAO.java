package ud2.avaliaciondiaria;

import java.util.List;

public interface RegstroEjerciciosDAO {
    public List<RegistroEjerciciosDTO> listarTodos();
    public boolean insertar(RegistroEjerciciosDTO ejercicio);
    public boolean eliminar(RegistroEjerciciosDTO ejercicio);
    public boolean actualizar(RegistroEjerciciosDTO ejercicio);
}
