package ud1.ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ExportarImportarXML {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        boolean opcionCorrecta = true;
        while (opcionCorrecta) {
            System.out.println("MENU:");
            System.out.println("1. Importar XML");
            System.out.println("2. Exportar XML");
            System.out.println("3. Salir");
            System.out.print("Escoge una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    importarXml();
                    break;
                case 2:
                    exportarXml();
                    break;
                case 3:
                    System.out.println("Saliendo del programa");
                    opcionCorrecta = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        sc.close();

    }

    public static void exportarXml() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Guardar como");

        if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();
        List<Persona> lstPersonas = leerTodasLasPersonas();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element personas = documento.createElement("personas");
            documento.appendChild(personas);

            for (Persona p : lstPersonas) {
                Element persona = documento.createElement("persona");
                personas.appendChild(persona);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(p.nombre);
                persona.appendChild(nombre);

                Element edad = documento.createElement("edad");
                edad.setTextContent(String.valueOf(p.edad));
                persona.appendChild(edad);
            }

            Transformer transformador = TransformerFactory.newInstance().newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            transformador.transform(new DOMSource(documento), new StreamResult(archivo));

            System.out.println("Personas exportadas a " + archivo.getName());

        } catch (Exception e) {
            System.out.println("Error al exportar: " + e.getMessage());
        }
    }

    public static void importarXml() {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Selecciona el archivo XML a importar");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado ningún archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivo);

            Element raiz = documento.getDocumentElement();
            NodeList listaPersonas = raiz.getElementsByTagName("persona");

            List<Persona> personas = leerTodasLasPersonas();

            for (int i = 0; i < listaPersonas.getLength(); i++) {
                Element persona = (Element) listaPersonas.item(i);

                String nombre = persona.getElementsByTagName("nombre").item(0).getTextContent();
                String edad = persona.getElementsByTagName("edad").item(0).getTextContent();

                personas.add(new Persona(nombre, Integer.parseInt(edad)));
            }

            guardarPersonas(personas);
            System.out.println("Personas importadas correctamente");

        } catch (Exception e) {
            System.out.println("Error al importar: " + e.getMessage());
        }
    }

    public static void guardarPersonas(List<Persona> personas) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("personas.dat"))) {
            out.writeInt(personas.size());
            for (Persona p : personas) {
                out.writeObject(p);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static List<Persona> leerTodasLasPersonas() {

        List<Persona> personas = new ArrayList<>();
        File fichero = new File("personas.dat");

        if (!fichero.exists()) {
            return personas;
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fichero))) {

            int numeroPersonas = in.readInt();

            for (int i = 0; i < numeroPersonas; i++) {
                Persona p = (Persona) in.readObject();
                personas.add(p);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        return personas;
    }
}
