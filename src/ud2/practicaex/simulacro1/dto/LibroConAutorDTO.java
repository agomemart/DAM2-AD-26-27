package ud2.practicaex.simulacro1.dto;

public class LibroConAutorDTO {
    private int idLibro;
    private String tituloLibro;
    private int idAutor;
    private String nombreAutor;
    
    public LibroConAutorDTO(int idLibro, String tituloLibro, int idAutor, String nombreAutor) {
        this.idLibro = idLibro;
        this.tituloLibro = tituloLibro;
        this.idAutor = idAutor;
        this.nombreAutor = nombreAutor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
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

    @Override
    public String toString() {
        return "LibroConAutorDTO [idLibro=" + idLibro + ", tituloLibro=" + tituloLibro + ", idAutor=" + idAutor
                + ", nombreAutor=" + nombreAutor + "]";
    }

    
}
