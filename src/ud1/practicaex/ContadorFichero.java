package ud1.practicaex;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ContadorFichero {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("prueba.txt"))) {
            int contLineas = 0;
            int contPalabras = 0;
            int contCaracteres = 0;
            String linea;

            while ((linea = in.readLine()) != null) {
                contLineas++;
                String[] palabras = linea.split("\\s+");
                contPalabras += palabras.length;

                for (String palabra : palabras) {
                    contCaracteres += palabra.length();
                }
            }

            System.out.println("Lineas: " + contLineas);
            System.out.println("Palabras: " + contPalabras);
            System.out.println("Caracteres: " + contCaracteres);
        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
