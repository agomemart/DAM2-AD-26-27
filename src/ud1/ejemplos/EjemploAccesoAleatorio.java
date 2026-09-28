package ud1.ejemplos;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class EjemploAccesoAleatorio {
    public static void main(String[] args) {
        final int N = 10000;

        try (var out = new DataOutputStream(new FileOutputStream("Nnumeros.dat"))) {
            Random rnd = new Random();

            for (int i = 0; i < N; i++) {
                out.writeInt(rnd.nextInt());
            }
            
        }catch (FileNotFoundException e) {
            System.out.println("No se puede crear el fichero");
        } catch (IOException e) {
            System.out.println("Error de E/S");
        } 

        int pos = 500;

        try (var in = new RandomAccessFile("Nnumeros.dat", "r")) {
            in.seek((pos - 1) * 4);
            System.out.println(in.readInt());
        } catch (Exception e) {
            // TODO: handle exception
        }

        try (var in = new RandomAccessFile("Nnumeros.dat", "rw")) {
            in.seek((pos - 1) * 4);
            in.writeInt(1);
            System.out.println(in.readInt());
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
