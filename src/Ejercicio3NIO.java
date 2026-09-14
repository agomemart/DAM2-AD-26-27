import java.io.File;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Ejercicio3NIO {
    private static final Scanner sc = new Scanner(System.in);

    private static int opcionMenu() {
        System.out.println("1. Crear un directorio");
        System.out.println("2. Listar todos los archivos y subdirectorios de un directorio");
        System.out.println("3. Eliminar un archivo o directorio");
        System.out.println("4. Mover o renombrar archivos y directorios");
        System.out.print("Escoge una opción: ");
        return sc.nextInt();
    }

    public static void main(String[] args) {

        int opcion = opcionMenu();

        while (opcion >= 1 && opcion <= 4) {
            switch (opcion) {
                case 1:
                    crearDirectorio();
                    break;
                case 2:
                    listarDirectorioRecursivo();
                    break;
                case 3:
                    eliminarArchivoODirectorio();
                    break;
                case 4:
                    moverArchivoODirectorio();
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            opcion = opcionMenu();
        }

    }

    private static void crearDirectorio() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setDialogTitle("Selecciona dónde crear el nuevo directorio");

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File padre = chooser.getSelectedFile();

            sc.nextLine();
            System.out.print("Nombre del nuevo directorio: ");
            String nombre = sc.nextLine();

            File nuevo = new File(padre, nombre);
            if (nuevo.mkdir()) {
                System.out.println("Directorio creado:  " + nuevo.getAbsolutePath());
            } else {
                System.out.println("No se pudo crear el directorio");
            }
        }
    }

    private static void listarDirectorioRecursivo() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File dir = chooser.getSelectedFile();
            System.out.println("Listado del directorio: " + dir.getAbsolutePath());
            listar(dir, "");
        }
    }

    private static void listar(File dir, String indent) {
        File[] contenido = dir.listFiles();
        if (contenido == null) {
            return;
        }

        for (File f : contenido) {
            System.out.println(indent + (f.isDirectory() ? "[DIR] " : "") + f.getName());
            if (f.isDirectory()) {
                listar(f, indent + " ");
            }
        }
    }

    private static void eliminarArchivoODirectorio() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File seleccionado = chooser.getSelectedFile();
            borrarRecursivo(seleccionado);
        }
    }

    private static void borrarRecursivo(File f) {
        if (f.isDirectory()) {
            File[] contenido = f.listFiles();
            if (contenido != null) {
                for (File hijo : contenido) {
                    borrarRecursivo(hijo);
                }
            }
        }
        if (f.delete()) {
            System.out.println("Borrado: " + f.getAbsolutePath());
        } else {
            System.out.println("No se pudo borrar " + f.getAbsolutePath());
        }
    }

    private static void moverArchivoODirectorio() {
        JFileChooser origenChooser = new JFileChooser();
        origenChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        origenChooser.setDialogTitle("Selecciona el archivo/directorio de origen");

        if (origenChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File origen = origenChooser.getSelectedFile();

        JFileChooser destinoChooser = new JFileChooser();
        destinoChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        destinoChooser.setDialogTitle("Selecciona la carpeta de destino");

        if (destinoChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File carpetaDestino = destinoChooser.getSelectedFile();

        sc.nextLine();
        System.out.print("Nuevo nombre (vacio para mantener el mismo): ");
        String nuevoNombre = sc.nextLine();
        if (nuevoNombre.isBlank()) {
            nuevoNombre = origen.getName();
        }

        File destino = new File(carpetaDestino, nuevoNombre);

        if (origen.renameTo(destino)) {
            System.out.println("Movido o renombrado a " + destino.getAbsolutePath());
        } else {
            System.out.println("Fallo al mover o renombrar");
        }
    }
}
