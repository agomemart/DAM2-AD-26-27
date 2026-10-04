package ud1.practicaex;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CifradoBinario {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("cifrado.dat"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("archivo.dat"))) {
            
            int b;
            while ((b = in.read()) != -1) {
                out.write(255 - b);
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
