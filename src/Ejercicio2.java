import java.io.File;

import javax.swing.JFileChooser;

public class Ejercicio2 {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int valRet = chooser.showOpenDialog(chooser);
        if (valRet == JFileChooser.APPROVE_OPTION) {
            File dir = chooser.getSelectedFile();

            System.out.println("Listado del directorio: " + dir.getAbsolutePath());

            int totalLength = 0;

            for (File f : dir.listFiles()) {
                System.out.println(f.getName() + " " + f.length() + " Directorio: " + f.isDirectory());
                if (f.isFile()) {
                    totalLength += f.length();
                }
            }
            System.out.println("Tamaño total: " + totalLength);
        }
    }
}
