package ud1.ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SerializandoColecciones {
 
    public static void main(String[] args) {
        File fichero = new File("listaPersonas.dat");
 
        if (!fichero.exists()) {
            crearFicheroInicial(fichero);
        }
 
        leerYMostrarLista(fichero);
    }
 
    public static void crearFicheroInicial(File fichero) {
        List<Persona> personas = new ArrayList<>();
        personas.add(new Persona("Ana", 20));
        personas.add(new Persona("Luis", 22));
        personas.add(new Persona("Marta", 19));
 
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fichero))) {
            out.writeObject(personas);
        } catch (Exception e) {
            System.out.println("Error al escribir: " + e.getMessage());
        }
    }
 
    public static void leerYMostrarLista(File fichero) {
 
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichero))) {
            List<Persona> personas = (List<Persona>) in.readObject();
 
            System.out.println("Lista de personas:");
            for (Persona p : personas) {
                System.out.println(p);
            }
            
        } catch (Exception e) {
            System.out.println("Error al leer: " + e.getMessage());
        }
    }
}