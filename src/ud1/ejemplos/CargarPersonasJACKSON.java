package ud1.ejemplos;

import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class CargarPersonasJACKSON {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Persona[] personas = mapper.readValue(new FileReader("persona.json"), Persona[].class);

            for (Persona persona : personas) {
                System.out.println(persona);
            }
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        

    }
}
