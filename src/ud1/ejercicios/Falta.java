package ud1.ejercicios;

public class Falta {
    String id;
    String nombre;
    String curso;
    String dataFalta;
    String tipoFalta;
    String materia;
    String sesion;
    boolean justificada;
    
    public Falta(String id, String nombre, String curso, String dataFalta, String tipoFalta, String materia,
            String sesion, boolean justificada) {
        this.id = id;
        this.nombre = nombre;
        this.curso = curso;
        this.dataFalta = dataFalta;
        this.tipoFalta = tipoFalta;
        this.materia = materia;
        this.sesion = sesion;
        this.justificada = justificada;
    }

    @Override
    public String toString() {
        String mensaje = nombre + " (" + curso + ") - " + dataFalta + " - " + materia;
        if (justificada) {
            mensaje += " - Justificada";
        } else {
            mensaje += " - Sin justificar";
        }
        return mensaje;
    }

    
}
