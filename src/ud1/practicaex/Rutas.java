package ud1.practicaex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Rutas {
    public static void main(String[] args) throws IOException {
        File f = new File("c:\\");

        System.out.println("Existe? " + f.exists());
        System.out.println(f.isFile() ? "Es archivo" : "Es directorio");
        System.out.println("Ruta absoluta: " + f.getAbsolutePath());
        System.out.println("Nombre: " + f.getName());
        System.out.println("Padre: " + f.getParent());
        System.out.println("Tamaño: " + f.length());
        System.out.println("Última modificación: " + f.lastModified());

        Path ruta = Paths.get("c:\\users");

        System.out.println("Existe? " + Files.exists(ruta));
        System.out.println(!Files.isDirectory(ruta) ? "Es archivo" : "Es directorio");
        System.out.println("Ruta absoluta: " + ruta.toAbsolutePath().toString());
        System.out.println("Nombre: " + ruta.getFileName());
        System.out.println("Padre: " + ruta.getParent());
        System.out.println("Tamaño: " + Files.size(ruta));
        System.out.println("Última modificación: " + Files.getLastModifiedTime(ruta));
    }
}
