package ud1.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FaltasAlumnado {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el archivo");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos CSV", "csv");
        chooser.setFileFilter(filtro);

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (var in = new BufferedReader(new FileReader(archivo))) {
            List<Falta> faltas = new ArrayList<>();

            String linea = in.readLine();
            while ((linea = in.readLine()) != null) {
                String[] partes = linea.split(",");
                boolean justificada = false;

                if (partes[7].equals("Si")) {
                    justificada = true;
                }

                Falta f = new Falta(partes[0], partes[1], partes[2], partes[3], partes[4], partes[5], partes[6],
                        justificada);
                faltas.add(f);
            }

            Map<String, Integer> faltasPorAlumnoYMateria = new TreeMap<>();
            for (Falta f : faltas) {
                if (f.curso.equalsIgnoreCase("2dam") && !f.justificada) {
                    String clave = f.nombre + " - " + f.materia;
                    if (faltasPorAlumnoYMateria.containsKey(clave)) {
                        faltasPorAlumnoYMateria.put(clave, faltasPorAlumnoYMateria.get(clave) + 1);
                    } else {
                        faltasPorAlumnoYMateria.put(clave, 1);
                    }
                }
            }

            for (String clave : faltasPorAlumnoYMateria.keySet()) {
                System.out.println(clave + ": " + faltasPorAlumnoYMateria.get(clave));
            }


        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe o no es válido");
        } catch (IOException e) {
            System.out.println("Error de E/S");
        }

    }
}
