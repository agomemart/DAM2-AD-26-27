package ud1.ejercicios;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ComparaRendimiento {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.print("Número de bytes a escribir (Enter para 1.000.000): ");
        String textoBytes = sc.nextLine();
        int numeroBytes = 1000000;
        if (!textoBytes.isEmpty()) {
            numeroBytes = Integer.parseInt(textoBytes);
        }

        System.out.print("Tamaño del búfer (Enter para 8192): ");
        String textoBuffer = sc.nextLine();
        int tamanoBuffer = 8192;
        if (!textoBuffer.isEmpty()) {
            tamanoBuffer = Integer.parseInt(textoBuffer);
        }

        long tiempoDirecto = escribirDirecto(numeroBytes);
        long tiempoConBuffer = escribirConBuffer(numeroBytes, tamanoBuffer);

        System.out.println();
        System.out.println("Bytes escritos: " + numeroBytes);
        System.out.println("Tiempo con FileOutputStream directo: " + tiempoDirecto + " ns");
        System.out.println("Tiempo con BufferedOutputStream (buffer " + tamanoBuffer + "): " + tiempoConBuffer + " ns");

        guardarResultado(numeroBytes, tamanoBuffer, tiempoDirecto, tiempoConBuffer);
        sc.close();
    }

    // Escribe los bytes directamente, sin buffer, y devuelve el tiempo empleado en nanosegundos
    public static long escribirDirecto(int numeroBytes) throws IOException {

        long inicio = System.nanoTime();

        FileOutputStream out = new FileOutputStream("directo.dat");
        for (int i = 0; i < numeroBytes; i++) {
            out.write(65); // el valor del byte no importa para medir el tiempo, escribimos siempre el mismo
        }
        out.close();

        long fin = System.nanoTime();
        return fin - inicio;
    }

    // Escribe los bytes usando un BufferedOutputStream y devuelve el tiempo empleado en nanosegundos
    public static long escribirConBuffer(int numeroBytes, int tamanoBuffer) throws IOException {

        long inicio = System.nanoTime();

        FileOutputStream out = new FileOutputStream("conBuffer.dat");
        BufferedOutputStream outBuffer = new BufferedOutputStream(out, tamanoBuffer);

        for (int i = 0; i < numeroBytes; i++) {
            outBuffer.write(65);
        }
        outBuffer.close();

        long fin = System.nanoTime();
        return fin - inicio;
    }

    // Añade una línea con los resultados de esta prueba al final de PruebasRendimiento.txt
    public static void guardarResultado(int numeroBytes, int tamanoBuffer, long tiempoDirecto, long tiempoConBuffer) throws IOException {

        FileWriter in = new FileWriter("PruebasRendimiento.txt", true);
        BufferedWriter out = new BufferedWriter(in);

        out.write("Bytes: " + numeroBytes + ", buffer: " + tamanoBuffer
                + ", directo: " + tiempoDirecto + " ns, con buffer: " + tiempoConBuffer + " ns");
        out.newLine();

        out.close();
    }
}