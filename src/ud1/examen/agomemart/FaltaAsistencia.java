package ud1.examen.agomemart;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author Adrián Gómez
 * 
 */
public class FaltaAsistencia implements Serializable {
    private final static long serialVersionUID = 1L;
    LocalDate data;
    int sesion;
    String modulo;
    String curso;
    String Alumno;

    public FaltaAsistencia(LocalDate data, int sesion, String modulo, String curso, String alumno) {
        this.data = data;
        this.sesion = sesion;
        this.modulo = modulo;
        this.curso = curso;
        Alumno = alumno;
    }

    @Override
    public String toString() {
        return "FaltaAsistencia [data=" + data + ", sesion=" + sesion + ", modulo=" + modulo + ", curso=" + curso
                + ", Alumno=" + Alumno + "]";
    }

}
