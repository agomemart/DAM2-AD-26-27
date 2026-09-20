package ud1.ejercicios;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;

public class ComparadorFicheros {
    public static void main(String[] args) {
        JFileChooser selector1 = new JFileChooser();
        selector1.setDialogTitle("Selecciona el primer archivo");

        if (selector1.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File fichero1 = selector1.getSelectedFile();

        JFileChooser selector2 = new JFileChooser();
        selector2.setDialogTitle("Selecciona el primer archivo");

        if (selector2.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File fichero2 = selector1.getSelectedFile();

        try (BufferedReader in = new BufferedReader(new FileReader(fichero1));
                BufferedReader in2 = new BufferedReader(new FileReader(fichero2))) {

            String lineaF1 = in.readLine();
            String lineaF2 = in2.readLine();

            while (lineaF1 != null && lineaF2 != null) {
                int longMin = Math.min(lineaF1.length(), lineaF2.length());

                for (int i = 0; i < longMin; i++) {
                    if (lineaF1.charAt(i) != lineaF2.charAt(i)) {
                        System.out.println("Los archivos son diferentes");
                        return;
                    }
                }
                
                if (lineaF1.length() != lineaF2.length()) {
                    System.out.println("Los archivos son diferentes");
                    return;
                }

                lineaF1 = in.readLine();
                lineaF2 = in2.readLine();
            }

            if (lineaF1 != null || lineaF2 != null) {
                System.out.println("Los archivos son diferentes");
                return;
            }

            System.out.println("Los archivos son idénticos");

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
