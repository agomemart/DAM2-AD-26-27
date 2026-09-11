import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;

public class Ejercicio3 {
    private static int opcionMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Crear un directorio");
        System.out.println("2. Listar todos los archivos y subdirectorios de un directorio");
        System.out.println("3. Eliminar un archivo o directorio");
        System.out.println("4. Mover o renombrar archivos y directorios");
        System.out.print("Escoge una opción: ");
        int opcion = sc.nextInt();
        return opcion;
    }

    public static void main(String[] args) {

        int opcion = opcionMenu();

        while (opcion >= 1 && opcion <= 4) {
            switch (opcion) {
                case 1:
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    int valRet = chooser.showOpenDialog(chooser);
                    if (valRet == JFileChooser.APPROVE_OPTION) {
                        File dir = chooser.getSelectedFile();
                        try {
                            dir.createNewFile();
                        } catch (IOException e) {
                            System.out.println("No se pudo crear el fichero: " + e.getMessage());
                        }
                    }
                    break;
                case 2:
                    JFileChooser chooser2 = new JFileChooser();
                    chooser2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                    int valRet2 = chooser2.showOpenDialog(chooser2);
                    if (valRet2 == JFileChooser.APPROVE_OPTION) {
                        File dir = chooser2.getSelectedFile();

                        System.out.println("Listado del directorio: " + dir.getAbsolutePath());

                        for (File f : dir.listFiles()) {
                            System.out.println(f.getName());
                        }
                    }
                    break;
                case 3:
                    JFileChooser chooser3 = new JFileChooser();
                    
                    int valRet3 = chooser3.showOpenDialog(chooser3);
                    if (valRet3 == JFileChooser.APPROVE_OPTION) {
                        File dir = chooser3.getSelectedFile();

                        if (dir.isDirectory()) {
                            dir.delete();
                        }

                        dir.delete();
                    }

                    break;
                case 4:
                    JFileChooser rutaEntrada = new JFileChooser();

                    int valRet4 = rutaEntrada.showOpenDialog(rutaEntrada);
                    if (valRet4 == JFileChooser.APPROVE_OPTION) {
                        File dir = rutaEntrada.getSelectedFile();
                        if (dir.isDirectory()) {
                            
                        }
                        dir.renameTo(dir);
                    }

                    JFileChooser rutaSalida = new JFileChooser();
                    int valRet5 = rutaSalida.showOpenDialog(rutaSalida);
                    if (valRet5 == JFileChooser.APPROVE_OPTION) {
                        
                    }
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            opcion = opcionMenu();
        }

    }
}
