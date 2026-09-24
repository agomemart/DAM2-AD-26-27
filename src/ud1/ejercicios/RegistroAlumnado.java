package ud1.ejercicios;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Random;

public class RegistroAlumnado {

    public static void main(String[] args) {

        Random rnd = new Random();

        try (BufferedWriter out = new BufferedWriter(new FileWriter("alumnos.txt"))) {
            for (int i = 1; i <= 1000; i++) {
                double nota = rnd.nextDouble() * 10;
                nota = Math.round(nota * 10) / 10.0;

                out.write("Alumno_" + i + ";" + nota);
                out.newLine();
            }

            System.out.println("Fichero alumnos.txt generado con 1000 alumnos");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
