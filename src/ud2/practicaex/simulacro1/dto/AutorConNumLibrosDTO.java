package ud2.practicaex.simulacro1.dto;

public class AutorConNumLibrosDTO {
    private int idAutor;
    private String nombreAutor;
    private int numLibros;
    
    public AutorConNumLibrosDTO(int idAutor, String nombreAutor, int numLibros) {
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
        this.numLibros = numLibros;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public int getNumLibros() {
        return numLibros;
    }

    public void setNumLibros(int numLibros) {
        this.numLibros = numLibros;
    }

    @Override
    public String toString() {
        return "AutorConNumLibrosDTO [idAutor=" + idAutor + ", nombreAutor=" + nombreAutor + ", numLibros=" + numLibros
                + "]";
    }

    

}
