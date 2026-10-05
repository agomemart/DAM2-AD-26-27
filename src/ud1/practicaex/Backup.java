package ud1.practicaex;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Backup {
    public static void main(String[] args) {
        File f = new File(System.getProperty("user.dir"));
        File destino = new File("backup");
        if (!destino.exists()) {
            destino.mkdirs();
        }

        if (!f.isDirectory()) {
            System.out.println("Seleccione un direcorio");
            return;
        }

        FileFilter filtro = new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() &&
                        file.getName().endsWith(".txt");
            }
        };

        File[] archivos = f.listFiles(filtro);

        if (archivos != null) {
            for (File file : archivos) {
                File archivoDestino = new File(destino, file.getName());
                try {
                    Files.copy(file.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("Archivo copiado: " + file.getName());
                } catch (IOException e) {
                    System.out.println("Error de E/S: " + e.getMessage());
                }
            }
        }
    }

}
