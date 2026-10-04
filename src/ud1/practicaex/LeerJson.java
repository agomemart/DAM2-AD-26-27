package ud1.practicaex;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class LeerJson {
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Creamos el archivo donde queremos almacenar el JSON
            File archivoJson = new File("biblioteca.json");

            // leemos el archivo JSON
            Biblioteca bLeida = mapper.readValue(archivoJson, Biblioteca.class);

            List<Libro> libros = bLeida.getLibros();
            System.out.println("LECTURA CON JACKSON");
            System.out.println("Biblioteca: " + bLeida.getNombre());
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha: " + libro.getFechaPublicacion());
                System.out.println("Etiquetas: " + libro.getEtiquetas());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            Gson gson = new Gson();
            FileReader reader = new FileReader("biblioteca.json");
            Biblioteca bLeido = gson.fromJson(reader, Biblioteca.class);
            reader.close();

            List<Libro> libros = bLeido.getLibros();
            System.out.println("LECTURA CON GSON");
            System.out.println("Biblioteca: " + bLeido.getNombre());
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Fecha: " + libro.getFechaPublicacion());
                System.out.println("Etiquetas: " + libro.getEtiquetas());
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
