import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import javax.swing.JFileChooser;

public class ComparadorFicheros {
    public static void main(String[] args) {
        JFileChooser selector1 = new JFileChooser();
        selector1.setDialogTitle("Selecciona el primer archivo");

        if (selector1.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File fichero1 = selector1.getSelectedFile();

        JFileChooser selector2 = new JFileChooser();
        selector2.setDialogTitle("Selecciona el primer archivo");

        if (selector2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File fichero2 = selector1.getSelectedFile();

        try (BufferedReader in = new BufferedReader(new FileReader(fichero1));
            BufferedReader in2 = new BufferedReader(new FileReader(fichero2))) {
            List<String> lineasFichero1 = in.readAllLines();
            List<String> lineasFichero2 = in2.readAllLines();

            if (lineasFichero1.size() != lineasFichero2.size()) {
                System.out.println("Los archivos son diferentes");
                return;
            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
