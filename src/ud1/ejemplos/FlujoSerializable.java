package ud1.ejemplos;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class FlujoSerializable {
    public static void main(String[] args) {
        Persona persona1 = new Persona("Pepe", LocalDate.of(2000, 1, 1), "abc123.");
        Persona persona2 = new Persona("Marta", LocalDate.of(2001, 2, 2), "12345");

        //Escribir objetos serializados
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("personasSerie.dat"))) {
            out.writeObject(persona1);
            out.writeObject(persona2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Leer objetos serializados
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("personasSerie.dat"))) {
            Persona p = (Persona)in.readObject();
            Persona p2 = (Persona)in.readObject();
            System.out.println(p);
            System.out.println(p2);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
