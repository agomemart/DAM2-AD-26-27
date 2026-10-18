package ud2.empresa;

import java.time.LocalDate;

public class ClienteDTO {
    int id;
    String nombre;
    String apellidos;
    LocalDate fechaAlta;
    boolean vip;
    
    public ClienteDTO(int id, String nombre, String apellidos, LocalDate fechaAlta, boolean vip) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
        this.vip = vip;
    }

    public ClienteDTO(int id) {
        this.id = id;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", fechaAlta=" + fechaAlta
                + ", vip=" + vip + "]";
    }

    
}
