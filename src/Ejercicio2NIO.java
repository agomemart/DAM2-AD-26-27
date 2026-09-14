import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.stream.Stream;

import javax.swing.JFileChooser;

public class Ejercicio2NIO {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path path = chooser.getSelectedFile().toPath();
            
            try (Stream<Path> stream = Files.list(path)){
                stream.forEach(System.out::println);

                
            } catch (IOException e) {
            }
            

            System.out.println("Listado del directorio: " + path.toFile().getAbsolutePath());

            int totalLength = 0;

            for (File f : path.toFile().listFiles()) {
                System.out.println(f.getName() + " " + f.length() + " Directorio: " + f.isDirectory());
                if (f.isFile()) {
                    totalLength += f.length();
                }
            }
            System.out.println("Tamaño total: " + totalLength);
        }
    }
}
