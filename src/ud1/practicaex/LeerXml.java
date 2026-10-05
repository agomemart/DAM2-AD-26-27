package ud1.practicaex;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class LeerXml {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document documento = builder.parse(new File("biblioteca.xml"));

            NodeList libros = documento.getElementsByTagName("libro");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);
                System.out.println("Libro " + (i + 1));
                System.out.println("ISBN: " + libro.getAttribute("isbn"));
                System.out.println("Título: " + libro.getElementsByTagName("titulo").item(0).getTextContent());
                System.out.println("Autor: " + libro.getElementsByTagName("autor").item(0).getTextContent());
                System.out.println("Precio: " + libro.getElementsByTagName("precio").item(0).getTextContent());
            }

        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
