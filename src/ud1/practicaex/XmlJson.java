package ud1.practicaex;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class XmlJson {
    public static void main(String[] args) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.dat"));
                FileWriter writer = new FileWriter("productos.json")) {
            int numRegistros = in.readInt();

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element productos = documento.createElement("productos");
            documento.appendChild(productos);

            List<Producto> listaProductos = new ArrayList<>();

            for (int i = 0; i < numRegistros; i++) {
                Producto p = (Producto) in.readObject();
                listaProductos.add(p);

                Element producto = documento.createElement("producto");
                producto.setAttribute("id", Integer.toString(p.getId()));
                productos.appendChild(producto);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(p.getNombre());
                producto.appendChild(nombre);

                Element precio = documento.createElement("precio");
                precio.setTextContent(Double.toString(p.getPrecio()));
                producto.appendChild(precio);
            }

            gson.toJson(listaProductos, writer);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(new DOMSource(documento), new StreamResult(new File("productos.xml")));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
