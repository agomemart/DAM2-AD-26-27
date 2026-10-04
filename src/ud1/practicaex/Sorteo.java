package ud1.practicaex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorteo {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("alumos.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("participaciones.txt", true))) {
            List<String> alumnos = new ArrayList<>();
            String linea;
            while ((linea = in.readLine()) != null) {
                alumnos.add(linea);
            }

            Random rnd = new Random();
            int numAleatorio = rnd.nextInt(alumnos.size());
            out.write(alumnos.get(numAleatorio) + " - " + LocalDateTime.now());



        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
