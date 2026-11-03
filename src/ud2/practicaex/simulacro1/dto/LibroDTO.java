package ud2.practicaex.simulacro1.dto;

public class LibroDTO {
    private int id;
    private int isbn;
    private String titulo;
    private Integer anio;
    private Double precio;
    private boolean disponible;
    private Integer idAutor;
    
    public LibroDTO(int id, int isbn, String titulo, Integer anio, Double precio, boolean disponible, Integer idAutor) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.precio = precio;
        this.disponible = disponible;
        this.idAutor = idAutor;
    }

    public LibroDTO(int isbn, String titulo, Integer anio, Double precio, boolean disponible, Integer idAutor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.precio = precio;
        this.disponible = disponible;
        this.idAutor = idAutor;
    }

    public LibroDTO(int id, int isbn, String titulo, Integer anio, Double precio, boolean disponible) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.anio = anio;
        this.precio = precio;
        this.disponible = disponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Integer getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(Integer idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "LibroDTO [id=" + id + ", isbn=" + isbn + ", titulo=" + titulo + ", anio=" + anio + ", precio=" + precio
                + ", disponible=" + disponible + ", idAutor=" + idAutor + "]";
    }

    
    
}
