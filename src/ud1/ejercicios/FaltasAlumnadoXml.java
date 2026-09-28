package ud1.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FaltasAlumnadoXml {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Seleccione el archivo de faltas:");

        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos CSV", "csv");
        chooser.setFileFilter(filtro);

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
            System.out.println("No se ha seleccionado un archivo");
            return;
        }

        File archivo = chooser.getSelectedFile();

        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
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
                System.out.println(f.toString());

            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();

            Element alumnos = doc.createElement("alumnos");
            doc.appendChild(alumnos);

            for (Falta falta : faltas) {
                Element alumno = doc.createElement("alumno");
                alumno.setAttribute("id", falta.id);
                alumnos.appendChild(alumno);

                Element nombre = doc.createElement("nombre");
                nombre.setTextContent(falta.nombre);
                alumno.appendChild(nombre);

                Element curso = doc.createElement("curso");
                curso.setTextContent(falta.curso);
                alumno.appendChild(curso);

                Element faltasXml = doc.createElement("faltas");
                alumno.appendChild(faltasXml);

                Element faltaXml = doc.createElement("falta");
                faltasXml.setAttribute("fecha", falta.dataFalta);
                faltasXml.setAttribute("tipo", falta.tipoFalta);
                faltasXml.setAttribute("sesion", falta.sesion);
                faltasXml.appendChild(faltaXml);

                Element materia = doc.createElement("materia");
                materia.setTextContent(falta.materia);
                faltaXml.appendChild(materia);

                Element justificada = doc.createElement("justificada");
                if (falta.justificada) {
                    justificada.setTextContent("Si");
                } else {
                    justificada.setTextContent("No");
                }
                faltaXml.appendChild(justificada);
            }

            TransformerFactory tFactory = TransformerFactory.newInstance();

            Transformer transformer = tFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);

            StreamResult resultado = new StreamResult(new File("alumnos.xml"));

            transformer.transform(source, resultado);

            System.out.println("XML generado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("No existe el archivo");
        } catch (IOException e) {
            System.out.println("Error de E/S");
        } catch (ParserConfigurationException e) {
            System.out.println("Error ParserConfigurationException: " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("Error TransformerConfigurationException: " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Error TransformerException: " + e.getMessage());
        }

    }
}
