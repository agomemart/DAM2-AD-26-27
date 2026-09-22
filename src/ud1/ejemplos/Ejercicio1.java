package ud1.ejemplos;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Ejercicio1 {
    public static void main(String[] args) {
        String ruta = "src/prueba.txt";
        File f = new File(ruta);

        if (f.exists()) {
            System.out.println("\tRuta absoluta: " + f.getAbsolutePath());
            System.out.println("\n\tNombre: " + f.getName());
            System.out.println("\tTamaño: " + f.length() + " bytes");
            SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
            System.out.println("\tModificado por última vez: " +
                    formateador.format(new Date(f.lastModified())));
        } else {
            System.out.println("El archivo no existe. Creándolo...");
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("No se pudo crear el fichero: " + e.getMessage());
            }
        }
    }
}
