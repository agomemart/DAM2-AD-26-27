package ud1.practicaex;

import java.io.File;
import java.util.Scanner;

public class FichaListado {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Inserta una ruta: ");
        String ruta = sc.nextLine();

        File directorio = new File(ruta);

        if (!directorio.exists()) {
            System.out.println("No existe el directorio introducido.");
            return;
        }

        if (directorio.isFile()) {
            System.out.println("La ruta es de un archivo");
            System.out.println("Nombre: " + directorio.getName());
            System.out.println("Ruta absoluta: " + directorio.getAbsolutePath());
            System.out.println("Tamaño: " + directorio.length());
            System.out.println("Última modificación: " + directorio.lastModified());
        } else {
            System.out.println("La ruta es un directorio");
            int sumaTamano = 0;
            for (File f : directorio.listFiles()) {
                if (f.isDirectory()) {
                    long tamano = calcularTamano(f);
                    sumaTamano += tamano;
                    System.out.println(f.getName() + " (Carpeta) Tamaño: " + tamano);
                } else {
                    System.out.println(f.getName() + " (Archivo) Tamaño: " + f.length());
                    sumaTamano += f.length();
                }
            }
            System.out.println("Tamaño total: " + sumaTamano);
        }
    }

    public static long calcularTamano(File file) {
        long tamano = 0;

        File[] archivos = file.listFiles();

        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    tamano += archivo.length();
                } else if (archivo.isDirectory()) {
                    tamano += calcularTamano(archivo);
                }
            }
        }

        return tamano;
    }
}
