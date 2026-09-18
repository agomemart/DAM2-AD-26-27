import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

public class AnalisisTexto {
    public static boolean esVocal(char c) {
        c = Character.toLowerCase(c);
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'á' || c == 'é' || c == 'í' || c == 'ó' || c == 'ú') {
            return  true;
        }

        return false;
    }

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona un archivo");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File fichero = chooser.getSelectedFile();

        try (BufferedReader in = new BufferedReader(new FileReader(fichero))) {
            String caracteres = in.readAllAsString();
            int numCaracteres = 0;
            int numVocales = 0;
            int numConsonantes = 0;
            int numDigitos = 0;
            int numEspacios = 0;

            for (int i = 0; i < caracteres.length(); i++) {
                char c = caracteres.charAt(i);
                if (Character.isDigit(c)) {
                    numDigitos++;
                } else if(Character.isWhitespace(c)) {
                    numEspacios++;
                } else if (esVocal(c)){
                    numVocales++;
                } else if (Character.isLetter(c) && !esVocal(c)) {
                    numConsonantes++;
                }
                numCaracteres++;
            }

            System.out.println("Número de caracteres: " + numCaracteres);
            System.out.println("Número de vocales: " + numVocales);
            System.out.println("Número de consonantes: " + numConsonantes);
            System.out.println("Número de dígitos: " + numDigitos);
            System.out.println("Número de espacios: " + numEspacios);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
