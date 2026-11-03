package ud2.practicaex.simulacro1.dto;

public class AutorDTO {
    private int id;
    private String nombre;
    private String nacionalidad;

    public AutorDTO(int id, String nombre, String nacionalidad) {
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public AutorDTO(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public String toString() {
        return "AutorDTO [id=" + id + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
    }

}
