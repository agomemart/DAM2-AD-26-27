package ud1.ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SerializandoPersonas {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        boolean continuar = true;

        while (continuar) {
            mostrarMenu();

            System.out.print("Escoge una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    addPersona();
                    break;
                case 2:
                    mostrarPersonas();
                    break;
                case 3:
                    buscarPorNombre();
                    break;
                case 4:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Opción no válida");

            }
        }
        System.out.println("Fin del programa");

    }

    public static void mostrarMenu() {
        System.out.println("MENU:");
        System.out.println("1. Añadir persona");
        System.out.println("2. Mostrar Personas");
        System.out.println("3. Buscar persona por nombre");
        System.out.println("4. Salir");
    }

    public static void addPersona() {
        System.out.print("Nombre de la persona: ");
        String nombre = sc.nextLine();
        System.out.print("Edad de la persona: ");
        int edad = sc.nextInt();
        sc.nextLine();

        Persona p = new Persona(nombre, edad);

        List<Persona> personas = leerTodasLasPersonas();
        personas.add(p);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("personas.dat", true))) {
            for (Persona persona : personas) {
                out.writeObject(persona);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public static void mostrarPersonas() {
        List<Persona> personas = leerTodasLasPersonas();

        if (personas.isEmpty()) {
            System.out.println("No hay personas guardadas todavía");
            return;
        }

        for (Persona p : personas) {
            System.out.println(p);
        }
    }

    public static void buscarPorNombre() {
        System.out.print("Nombre de la persona a buscar: ");
        String nombre = sc.nextLine();

        List<Persona> personas = leerTodasLasPersonas();
        boolean encontrada = false;

        for (Persona p : personas) {
            if (p.nombre.equalsIgnoreCase(nombre)) {
                System.out.println("Persona encontrada: " + p.nombre);
                encontrada = true;
            }
        }

        if (!encontrada) {
            System.out.println("No se ha encontrado ninguna persona con ese nombre");
        }
    }

    public static List<Persona> leerTodasLasPersonas() {

        List<Persona> personas = new ArrayList<>();
        File fichero = new File("personas.dat");

        if (!fichero.exists()) {
            return personas;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichero))) {
            int numeroPersonas = in.readInt();

            for (int i = 0; i < numeroPersonas; i++) {
                Persona p = (Persona) in.readObject();
                personas.add(p);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return personas;
    }
}
