package ud1.practicaex;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

public class ModificarXml {
    public static void main(String[] args) {

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File("biblioteca.xml"));

            NodeList libros = documento.getElementsByTagName("libro");

            for (int i = 0; i < libros.getLength(); i++) {
                Element libro = (Element) libros.item(i);

                double precio = Double.parseDouble(libro.getElementsByTagName("precio").item(0).getTextContent());
                precio = precio + precio * 0.1;
                libro.getElementsByTagName("precio").item(0).setTextContent(Double.toString(precio));

                if (libro.getAttribute("isbn").equalsIgnoreCase("978-2")) {
                    libro.getParentNode().removeChild(libro);
                }
            }

            Element biblioteca = documento.getDocumentElement();
            Element libro = documento.createElement("libro");
            libro.setAttribute("isbn", "978-3");
            biblioteca.appendChild(libro);

            Element titulo = documento.createElement("titulo");
            titulo.setTextContent("Libro1");
            libro.appendChild(titulo);

            Element autor = documento.createElement("autor");
            autor.setTextContent("Autor1");
            libro.appendChild(autor);

            Element precio = documento.createElement("precio");
            precio.setTextContent("12.4");
            libro.appendChild(precio);

            Element fechaPublicacion = documento.createElement("fechaPublicacion");
            fechaPublicacion.setTextContent("2026-02-05");
            libro.appendChild(fechaPublicacion);

            Element etiquetas = documento.createElement("etiquetas");
            libro.appendChild(etiquetas);

            Element etiqueta = documento.createElement("etiqueta");
            etiqueta.setTextContent("novela");
            etiquetas.appendChild(etiqueta);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(new DOMSource(documento), new StreamResult(new File("biblioteca.xml")));
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TransformerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
