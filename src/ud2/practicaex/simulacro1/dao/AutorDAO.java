package ud2.practicaex.simulacro1.dao;

import java.util.List;

import ud2.practicaex.simulacro1.dto.AutorConNumLibrosDTO;
import ud2.practicaex.simulacro1.dto.AutorDTO;

public interface AutorDAO {
    public int insertar(AutorDTO autor);
    public AutorDTO buscarPorId(int id);
    public List<AutorDTO> listarTodos();
    public boolean actualizar(AutorDTO autor);
    public boolean eliminar(int id);

    //Más funcionalidades
    public List<AutorConNumLibrosDTO> listarConNumLibros();
}
