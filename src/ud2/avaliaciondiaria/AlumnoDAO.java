package ud2.avaliaciondiaria;

import java.util.List;

public interface AlumnoDAO {
    public List<AlumnoDTO> listarTodos();
    public boolean insertar(AlumnoDTO alumno);
    public boolean eliminar(AlumnoDTO alumno);
    public boolean actualizar(AlumnoDTO alumno);
}
