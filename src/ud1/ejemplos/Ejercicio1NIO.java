package ud1.ejemplos;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio1NIO {
    public static void main(String[] args) {
        Path path = Path.of("prueba.txt");
        if (Files.exists(path)) {
            System.out.println(path.toAbsolutePath());
            System.out.println(path.getFileName());
            try {
                System.out.println(Files.size(path) + " bytes");
                System.out.println("Última modificación: " + Files.getLastModifiedTime(path));
            } catch (IOException e) {
            }
            System.out.println("Es directorio? " + Files.isDirectory(path));
        } else {
            System.out.println("El archivo no existe. Creándolo...");
            try {
                Files.createFile(path);
            } catch (IOException e) {
                System.out.println("No se pudo crear el fichero: " + e.getMessage());
            }
        }
    }
}
