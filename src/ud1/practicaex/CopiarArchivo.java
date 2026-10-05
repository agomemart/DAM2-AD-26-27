package ud1.practicaex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CopiarArchivo {
    public static void main(String[] args) {
        File origen = new File("C:\\Users\\Anxo\\Desktop\\AD");
        File dest = new File("D:\\");

        File[] archivos = origen.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile()) {
                File nuevoArchivo = new File(dest, archivo.getName());

                try {
                    Files.copy(archivo.toPath(), nuevoArchivo.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println("Archivos copiados");
    }

    
}
