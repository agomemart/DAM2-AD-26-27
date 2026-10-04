package ud1.practicaex;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class AccesoAleatorio2 {
    public static void main(String[] args) {
        final int NUM_REGISTROS = 50;
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("datos.dat"))) {
            Random rnd = new Random();
            for (int i = 0; i < NUM_REGISTROS; i++) {
                out.writeInt(rnd.nextInt());
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {

        }

        try (RandomAccessFile in = new RandomAccessFile("datos.dat", "rw")) {

            in.seek(20);
            System.out.println("Posición: " + in.getFilePointer());

            int n = in.readInt();
            System.out.println("Leído: " + n);

            System.out.println("Posición después de leer: " + in.getFilePointer());

            in.seek(20);
            in.writeInt(10);

            System.out.println("Posición después de escribir: " + in.getFilePointer());

            in.seek(20);
            System.out.println("Leído después: " + in.readInt());

        } catch (IOException e) {

        }

    }
}
