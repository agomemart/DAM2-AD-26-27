package ud1.ejemplos;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class CargarPersonasGSON {
    public static void main(String[] args) {
        Gson gson = new Gson();
        try {
            Persona[] personas = gson.fromJson(new FileReader("personas.json"), Persona[].class);

            for (Persona persona : personas) {
                System.out.println(persona);
            }

        } catch (JsonSyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonIOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
