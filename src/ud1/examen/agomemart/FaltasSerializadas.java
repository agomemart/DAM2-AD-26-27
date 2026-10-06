package ud1.examen.agomemart;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Adrián Gómez
 * 
 */
public class FaltasSerializadas {
    public static void main(String[] args) {
        List<FaltaAsistencia> listaFaltas = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader("DATOS/consultaxeradorinformes.csv"))) {
            String linea = in.readLine();

            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(";");
                String[] partesFecha = partes[3].split("/");

                for (int i = 0; i < partes.length; i++) {
                    partes[i] = partes[i].substring(1, partes[i].length() - 1);
                }

                LocalDate l = LocalDate.of(Integer.valueOf(partesFecha[2]), Integer.valueOf(partesFecha[1]),
                        Integer.valueOf(partesFecha[0]));
                FaltaAsistencia f = new FaltaAsistencia(l, Integer.valueOf(partes[6]), partes[5], partes[2], partes[1]);
                if (partes[7].equals("Non")) {
                    listaFaltas.add(f);
                    System.out.println(f);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("DATOS/FaltasAsistencia.dat"))) {
            out.write(listaFaltas.size());
            for (FaltaAsistencia f : listaFaltas) {
                out.writeObject(f);
            }
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        }
    }
}
