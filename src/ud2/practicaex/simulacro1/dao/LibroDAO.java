package ud2.practicaex.simulacro1.dao;

import java.util.List;

import ud2.practicaex.simulacro1.dto.LibroConAutorDTO;
import ud2.practicaex.simulacro1.dto.LibroDTO;

public interface LibroDAO {
    public int insertar(LibroDTO libro);
    public LibroDTO buscarPorId(int id);
    public List<LibroDTO> listarTodos();
    public boolean actualizar(LibroDTO libro);
    public boolean eliminar(int id);

    //Más funcionalidades
    List<LibroConAutorDTO> listarConAutor();
    List<LibroDTO> buscarPorAutor(int autorId);
}
