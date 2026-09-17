import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;

public class EjemploCopiaArchivos {

    public static void main(String[] args) {
        JFileChooser rutaInicio = new JFileChooser();
        JFileChooser rutaDestino = new JFileChooser();
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        File origen = null;
        File destino = null;
        rutaInicio.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int valRet = rutaInicio.showOpenDialog(rutaInicio);
        if (valRet == JFileChooser.APPROVE_OPTION) {
            origen = rutaInicio.getSelectedFile();
        }
        rutaDestino.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int valRet2 = rutaDestino.showOpenDialog(rutaDestino);
        if (valRet2 == JFileChooser.APPROVE_OPTION) {
            destino = rutaDestino.getSelectedFile();
        }

        
        try {
            in = new BufferedInputStream(new FileInputStream(origen));
            out = new BufferedOutputStream(new FileOutputStream(destino));
            int c;
            while ((c = in.read()) != -1) {
                out.write(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally { // Hay que cerrar el flujo en cualquier condición.
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}