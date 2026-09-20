package ud1.ejercicios;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JFileChooser;

public class LineasNumeradas {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona un archivo");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (BufferedReader in = new BufferedReader(new FileReader(archivo));
            BufferedWriter out = new BufferedWriter(new FileWriter("copiaArchivo.txt"))) {
            List<String> lineas = in.readAllLines();
            int cont = 0;
            for (String linea : lineas) {
                cont++;
                out.write(cont + ": " + linea);
                out.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
