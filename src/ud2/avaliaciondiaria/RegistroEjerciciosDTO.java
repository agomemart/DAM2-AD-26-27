package ud2.avaliaciondiaria;

import java.time.LocalDate;

public class RegistroEjerciciosDTO {
    private int id;
    private String nombre;
    private LocalDate fecha;
    private int idAlumno;
    private int puntuacion;
    
    public RegistroEjerciciosDTO(int id, String nombre, LocalDate fecha, int idAlumno, int puntuacion) {
        this.id = id;
        this.nombre = nombre;
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.puntuacion = puntuacion;
    }

    public RegistroEjerciciosDTO(String nombre, LocalDate fecha, int idAlumno, int puntuacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.idAlumno = idAlumno;
        this.puntuacion = puntuacion;
    }

    public RegistroEjerciciosDTO() {
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
