package ud1.ejemplos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ParticipacionEnClase {
    public static void main(String[] args) {
        System.out.println("PARTICIPACIÓN EN CLASE");
        System.out.println("**********************");
        try (BufferedReader in = new BufferedReader(new FileReader("alumnos.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("participacion.txt", true))) {
            List<String> alumnos = in.readAllLines();
            Random rnd = new Random();
            String alumnoElegido = alumnos.get(rnd.nextInt(alumnos.size()));
            out.write(alumnoElegido + " - " + LocalDateTime.now() + "\n");
            System.out.println("El elegido es..." + alumnoElegido);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
}