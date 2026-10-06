package ud1.examen.agomemart;

import java.util.List;

import com.google.gson.Gson;

/**
 * @author Adrián Gómez
 */
public class ExportarJsonEmpleados {
    public static boolean exportarJson(List<Empleado> empleados, String fichero) {
        List<Empleado> listaEmpleados = LeerEmpleados.leerEmpleados(fichero);
        if (listaEmpleados == null || listaEmpleados.size() == 0 || fichero == null || fichero.isBlank()) {
            return false;
        }
        Gson gson = new Gson();

        String jsonPersonas = gson.toJson(listaEmpleados);
        System.out.println(jsonPersonas);

        return true;
    }

    public static void main(String[] args) {
        List<Empleado> empleados = List.of(new Empleado(1, "Ana García", "Informática", 35000),
                new Empleado(2, "Luis Pérez", "Recursos Humanos", 28000),
                new Empleado(3, "María López", "Informática", 42));

        if (exportarJson(empleados, "DATOS/empleados.json")) {
            System.out.println("Fichero creado");
        } else {
            System.out.println("Error al crear el fichero");
        }
    }

}
