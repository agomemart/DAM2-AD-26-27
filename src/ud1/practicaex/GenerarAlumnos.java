package ud1.practicaex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerarAlumnos {
    final static int NUM_ALUMNOS = 1000;

    public static void main(String[] args) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("alumnosGenerados.txt"))) {
            Random rnd = new Random();

            for (int i = 1; i <= NUM_ALUMNOS; i++) {
                int nota = rnd.nextInt(11);
                out.write("Alumno_" + i + ";" + nota);
                out.newLine();
            }

            System.out.println("Fichero generado correctamente.");

        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        try (BufferedReader in = new BufferedReader(new FileReader("alumnosGenerados.txt"))) {
            int totalNotas = 0;
            int maxNota = Integer.MIN_VALUE;
            int minNota = Integer.MAX_VALUE;
            int numAprobados = 0;
            String alumnoMejorNota = "";
            String linea;

            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(";");
                int nota = Integer.valueOf(partes[1]);
                totalNotas += nota;

                if (nota >= 5) {
                    numAprobados++;
                }

                if (nota > maxNota) {
                    maxNota = nota;
                    alumnoMejorNota = partes[0];
                }

                if (nota < minNota) {
                    minNota = nota;
                }
            }

            double media = (double) totalNotas / NUM_ALUMNOS;

            System.out.println("ESTADÍSTICAS:");
            System.out.printf("Media: %.2f\n", media);
            System.out.println("Nota máxima: " + maxNota);
            System.out.println("Nota mínima: " + minNota);
            System.out.println("Número de aprobados: " + numAprobados);
            System.out.println("Nómbre alumno con la max nota: " + alumnoMejorNota);

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el fichero: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

    }
}
