package ud1.practicaex;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonasSerializadas {
    public static void main(String[] args) {
        File ficheroPersonas = new File("personas2.dat");
        List<Persona> personas = new ArrayList<>();
        if (ficheroPersonas.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ficheroPersonas))) {
                int numPersonas = in.readInt();

                for (int i = 0; i < numPersonas; i++) {
                    Persona p = (Persona) in.readObject();
                    personas.add(p);
                }

            } catch (FileNotFoundException e) {
                // TODO: handle exception
            } catch (IOException e) {
                // TODO: handle exception
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("MENU");
        System.out.println("1. Añadir");
        System.out.println("2. Mostrar");
        System.out.println("3. Buscar");
        System.out.print("Escoge una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("Edad: ");
                int edad = sc.nextInt();
                sc.nextLine();
                System.out.print("Contraseña: ");
                String contrasena = sc.nextLine();
                Persona p = new Persona(nombre, edad, contrasena);
                personas.add(p);

                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ficheroPersonas))) {
                    for (Persona persona : personas) {
                        out.writeInt(personas.size());
                        out.writeObject(persona);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
                break;
            case 2:
                for (Persona persona : personas) {
                    System.out.println(persona.toString());
                }
                break;
            case 3:
                System.out.print("Nombre de la persona a buscar: ");
                String nombreBuscar = sc.nextLine();

                boolean encontrada = false;
                for (Persona persona : personas) {
                    if (persona.getNombre().equalsIgnoreCase(nombreBuscar)) {
                        System.out.println("Persona encontrada: " + persona.toString());
                        encontrada = true;
                    }
                }

                if (!encontrada) {
                    System.out.println("No se ha encontrado a la persona con nombre: " + nombreBuscar);
                }

                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

        sc.close();
    }
}
