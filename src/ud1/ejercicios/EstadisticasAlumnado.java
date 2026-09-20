package ud1.ejercicios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EstadisticasAlumnado {

    public static void main(String[] args) {

        try (BufferedReader in = new BufferedReader(new FileReader("alumnos.txt"))) {

            int numeroAlumnos = 0;
            double sumaNotas = 0;
            double notaMaxima = -1;
            double notaMinima = 11;
            int aprobados = 0;
            String mejorAlumno = "";

            String linea;
            while ((linea = in.readLine()) != null) {

                String[] partes = linea.split(";");
                String nombre = partes[0];
                double nota = Double.parseDouble(partes[1]);

                sumaNotas = sumaNotas + nota;
                numeroAlumnos++;

                if (nota >= 5) {
                    aprobados++;
                }

                if (nota > notaMaxima) {
                    notaMaxima = nota;
                    mejorAlumno = nombre;
                }

                if (nota < notaMinima) {
                    notaMinima = nota;
                }
            }

            double notaMedia = sumaNotas / numeroAlumnos;

            System.out.println("Número de alumnos: " + numeroAlumnos);
            System.out.println("Nota media: " + notaMedia);
            System.out.println("Nota máxima: " + notaMaxima);
            System.out.println("Nota mínima: " + notaMinima);
            System.out.println("Aprobados: " + aprobados);
            System.out.println("Mejor alumno: " + mejorAlumno);

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}