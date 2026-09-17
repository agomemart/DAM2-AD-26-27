import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import javax.swing.JFileChooser;

public class Ejercicio2NIO {
    static long totalSize = 0;

    public static void imprime(Path p) {
        System.out.println("Nombre: " + p.getFileName());
        try {
            long size = Files.size(p);
            totalSize += size;
            System.out.print(" (" + Files.size(p) + ")");
            System.out.println(Files.isDirectory(p) ? "Directirio" : "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            Path path = chooser.getSelectedFile().toPath();
            
            try (Stream<Path> stream = Files.list(path)){
                stream.forEach(Ejercicio2NIO::imprime);

                System.out.println("Tamaño total: " + totalSize + " bytes");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
