import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FirmasHexadecimales {

    public static void main(String[] args) {

        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Selecciona un archivo");

        int opcion = selector.showOpenDialog(null);

        if (opcion != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File archivo = selector.getSelectedFile();
        comprobarFirma(archivo);
    }

    public static void comprobarFirma(File archivo) {

        String nombre = archivo.getName();
        String extension = obtenerExtension(nombre);

        String firmaPdf = "25504446";
        String firmaJpg = "FFD8FF";
        String firmaPng = "89504E47";

        String firmaEsperada = "";

        if (extension.equalsIgnoreCase("pdf")) {
            firmaEsperada = firmaPdf;
        } else if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")) {
            firmaEsperada = firmaJpg;
        } else if (extension.equalsIgnoreCase("png")) {
            firmaEsperada = firmaPng;
        } else {
            System.out.println("Extensión no reconocida: " + extension);
            return;
        }

        int numeroBytes = firmaEsperada.length() / 2;
        String firmaReal = leerFirma(archivo, numeroBytes);

        System.out.println("Archivo: " + nombre);
        System.out.println("Firma esperada: " + firmaEsperada);
        System.out.println("Firma real:     " + firmaReal);

        if (firmaReal.equals(firmaEsperada)) {
            System.out.println("La firma coincide con la extensión");
        } else {
            System.out.println("La firma NO coincide con la extensión");
        }
    }

    public static String obtenerExtension(String nombre) {
        int posicionPunto = nombre.lastIndexOf(".");
        return nombre.substring(posicionPunto + 1);
    }

    // Lee los primeros "numeroBytes" del archivo y los devuelve como texto hexadecimal
    public static String leerFirma(File archivo, int numeroBytes) {

        String firma = "";

        try {
            FileInputStream entrada = new FileInputStream(archivo);

            for (int i = 0; i < numeroBytes; i++) {

                int valor = entrada.read();
                String hexadecimal = Integer.toHexString(valor).toUpperCase();

                // Si el byte es pequeño (por ejemplo 5), toHexString da "5" en vez de "05"
                if (hexadecimal.length() == 1) {
                    hexadecimal = "0" + hexadecimal;
                }

                firma = firma + hexadecimal;
            }

            entrada.close();

        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
        }

        return firma;
    }
}