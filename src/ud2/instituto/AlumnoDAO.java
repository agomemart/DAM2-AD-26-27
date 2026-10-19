package ud2.instituto;

import java.util.List;

public interface AlumnoDAO {
    public List<AlumnoDTO> listarTodos();
    public boolean insertar(AlumnoDTO alumno);
    public boolean eliminar(int id);
    public boolean eliminar(AlumnoDTO alumno);
    public AlumnoDTO buscarPorId(int id);
    public boolean actualizar(AlumnoDTO alumno);

}
