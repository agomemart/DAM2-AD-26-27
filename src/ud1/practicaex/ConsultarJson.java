package ud1.practicaex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

public class ConsultarJson {
    public static void main(String[] args) {

        try {
            Gson gson = new Gson();

            FileReader reader = new FileReader("biblioteca.json");
            Biblioteca bLeida = gson.fromJson(reader, Biblioteca.class);
            reader.close();

            List<Libro> libros = bLeida.getLibros();
            double totalPrecios = 0;
            for (Libro libro : libros) {
                totalPrecios += libro.getPrecio();
                if (libro.getEtiquetas().contains("clásico")) {
                    System.out.println(libro.toString());
                }
            }
            double media = totalPrecios / libros.size();
            System.out.println("Media precio libros: " + media);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
