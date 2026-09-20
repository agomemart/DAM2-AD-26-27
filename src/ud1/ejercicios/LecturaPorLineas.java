package ud1.ejercicios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class LecturaPorLineas {

    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona un archivo");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();

        Scanner sc = new Scanner(System.in);
        System.out.print("Palabra clave a buscar: ");
        String palabraClave = sc.nextLine();
        sc.close();

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = in.readLine()) != null) {
                if (linea.contains(palabraClave)) {
                    System.out.println(linea);
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}