import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

public class CifradoBinario {
    public static void main(String[] args) {
        JFileChooser selector = new JFileChooser();
        selector.setDialogTitle("Selecciona el archivo a cifrar");

        if (selector.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File origen = selector.getSelectedFile();
        File destino = new File(origen.getParent(), origen.getName() + ".cif");

        cifrar(origen, destino);
    }

    private static void cifrar(File origen, File destino) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(origen));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destino))) {
            
            int byteLeido;

            while ((byteLeido = in.read()) != -1) {
                int byteInvertido = 255 - byteLeido;
                out.write(byteInvertido);
            }

            System.out.println("Proceso terminado");
            System.out.println("Archivo generado: " + destino.getName());
            System.out.println("Vuelve a ejecutar el programa sobre ese archivo para recuperar el original");

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
