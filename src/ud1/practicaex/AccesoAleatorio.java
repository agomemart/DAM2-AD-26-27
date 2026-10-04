package ud1.practicaex;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;

public class AccesoAleatorio {
    public static void main(String[] args) {
        final int NUM_A_GENERAR = 10000;
        Random rnd = new Random();

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("aleatorio.dat"))) {
            for (int i = 0; i < NUM_A_GENERAR; i++) {
                out.writeInt(rnd.nextInt());
            }
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            RandomAccessFile ra = new RandomAccessFile("aleatorio.dat", "r");
            
            int pos = 5;
            ra.seek(pos);
            int num = ra.readInt();
            System.out.println("Número: " + num);
            System.out.println("Byte: " + pos * 4);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
