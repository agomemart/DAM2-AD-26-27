package ud1.practicaex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TamanoCarpeta {
    public static void main(String[] args) {
        File f = new File("c:\\users");

        int tamanoTotal = 0;
       for (File archivo : f.listFiles()) {
            if (archivo.isFile()) {
                tamanoTotal += archivo.length();
            }
       }

       System.out.println("Tamaño total: " + tamanoTotal);

       Path p = Path.of("d:\\");
       /*try (var paths = Files.walk(p)) {
            long tamaño = Files.walk(p)
        .filter(Files::isRegularFile)
        .mapToLong(p -> {
            try {
                return Files.size(p);
            } catch (IOException e) {
                return 0;
            }
        })
        .sum();
       } catch (Exception e) {
        // TODO: handle exception
       }*/
    }
}
