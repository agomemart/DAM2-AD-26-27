package ud1.practicaex;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopiaBinaria {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("prueba.dat"));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("copiaPrueba.dat"))) {
            
            int b;
            while ((b = in.read()) != -1) {
                out.write(b);
            }

            System.out.println("Archivo copiado");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
