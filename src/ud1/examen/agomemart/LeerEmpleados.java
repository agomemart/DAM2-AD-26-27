package ud1.examen.agomemart;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Adrián Gómez
 */
public class LeerEmpleados {
    public static List<Empleado> leerEmpleados(String fichero) {
        List<Empleado> listaEmpleados = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(new File(fichero));

            Element empleados = documento.getDocumentElement();
            NodeList nodosEmpleados = empleados.getChildNodes();
            for (int i = 0; i < nodosEmpleados.getLength(); i++) {
                Element empleado = (Element) nodosEmpleados.item(i);

                String id = empleado.getAttribute("id");
                String nombre = empleado.getElementsByTagName("nombre").item(0).getTextContent();
                String departamento = empleado.getElementsByTagName("departamento").item(0).getTextContent();
                String salario = empleado.getElementsByTagName("salario").item(0).getTextContent();

                Empleado e = new Empleado(Integer.valueOf(id), nombre, departamento, Double.valueOf(salario));
                listaEmpleados.add(e);
            }

        } catch (ParserConfigurationException e) {
            System.out.println("Error ParserConfigurationException: " + e.getMessage());
        } catch (SAXException e) {
            System.out.println("Error SAXException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error IOException: " + e.getMessage());
        }

        return listaEmpleados;
    }

    public static void main(String[] args) {
        List<Empleado> listaEmpleados = leerEmpleados("DATOS/Empleados.xml");
        for (Empleado e : listaEmpleados) {
            System.out.println(e);
        }
    }
}
