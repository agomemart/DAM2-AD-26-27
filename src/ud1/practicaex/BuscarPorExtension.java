package ud1.practicaex;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

public class BuscarPorExtension {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Extension a buscar: ");
        String extension = sc.nextLine();
        System.out.print("Directorio en el que buscar: ");
        String directorio = sc.nextLine();
        sc.close();

        if (!extension.startsWith(".")) {
            extension = "." + extension;
        }

        String ext = extension;

        File f = new File(directorio);

        if (!f.isDirectory()) {
            System.out.println("El directorio no existe o no es un directorio.");
            return;
        }

        FileFilter filtro = file -> file.getName().endsWith(ext);

        int total = buscarArchivos(f, filtro);

        System.out.println("Total archivos: " + total);
    }

    public static int buscarArchivos(File directorio, FileFilter filtro) {
        int cont = 0;

        File[] archivos = directorio.listFiles();

        if (archivos == null) {
            return 0;
        }

        for (File archivo : archivos) {
            if (archivo.isDirectory()) {
                cont += buscarArchivos(archivo, filtro);
            } else if (filtro.accept(archivo)) {
                System.out.println(archivo.getAbsolutePath());
                cont++;
            }
        }

        return cont;
    }
}
