package ud1.practicaex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LineasNumeradas {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("prueba.txt"));
            BufferedWriter out = new BufferedWriter(new FileWriter("pruebaM.txt"))) {
            String linea;
            int cont = 1;
            while ((linea = in.readLine()) != null) {
                out.write(cont + ": " + linea);
                out.newLine();
                cont++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
