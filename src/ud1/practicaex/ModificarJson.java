package ud1.practicaex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ModificarJson {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("biblioteca.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Biblioteca bLeido = gson.fromJson(reader, Biblioteca.class);
            List<Libro> libros = bLeido.getLibros();
            Libro lPrecio = libros.get(0);
            lPrecio.setPrecio(15.56);
            List<String> etiquetas = new ArrayList<>();
            etiquetas.add("novela");
            Libro l = new Libro("985-7", "Libro1", "Autor1", "2026-10-4", 19.99, etiquetas);
            libros.add(l);
            Biblioteca b = new Biblioteca(bLeido.getNombre(), libros);
            FileWriter writer = new FileWriter("biblioteca.json");
            gson.toJson(b, writer);
            writer.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }
}
