package ud1.practicaex;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

import com.google.gson.Gson;
import java.io.File;

public class ConversionJsonXml {
    public static void main(String[] args) {

        try (FileReader reader = new FileReader("pedidos.json")) {
            Gson gson = new Gson();

            Tienda t = gson.fromJson(reader, Tienda.class);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.newDocument();

            Element tienda = documento.createElement("tienda");
            tienda.setAttribute("nombre", t.getTienda());
            documento.appendChild(tienda);

            List<Pedido> pedidos = t.getPedidos();
            for (Pedido p : pedidos) {
                Element pedido = documento.createElement("pedido");
                pedido.setAttribute("id", p.getId());
                pedido.setAttribute("pagado", String.valueOf(p.isPagado()));
                tienda.appendChild(pedido);

                Element fecha = documento.createElement("fecha");
                fecha.setTextContent(p.getFecha());
                pedido.appendChild(fecha);

                Cliente c = p.getCliente();
                Element cliente = documento.createElement("cliente");
                pedido.appendChild(cliente);

                Element nombre = documento.createElement("nombre");
                nombre.setTextContent(c.getNombre());
                cliente.appendChild(nombre);

                Element email = documento.createElement("email");
                email.setTextContent(c.getEmail());
                cliente.appendChild(email);

                Element lineas = documento.createElement("lineas");
                pedido.appendChild(lineas);

                double totalPedido = 0;
                List<Linea> listaLineas = p.getLineas();
                for (Linea l : listaLineas) {
                    Element linea = documento.createElement("linea");
                    linea.setAttribute("unidades", Integer.toString(l.getUnidades()));
                    lineas.appendChild(linea);

                    Element producto = documento.createElement("producto");
                    producto.setTextContent(l.getProducto());
                    linea.appendChild(producto);

                    Element precio = documento.createElement("precio");
                    precio.setTextContent(Double.toString(l.getPrecio()));
                    linea.appendChild(precio);

                    totalPedido += l.getPrecio() * l.getUnidades();
                }

                Element total = documento.createElement("total");
                total.setTextContent(Double.toString(totalPedido));
                pedido.appendChild(total);
            }

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.transform(new DOMSource(documento), new StreamResult(new File("pedidos.xml")));

        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            System.out.println("Error ParserConfigurationException: " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            System.out.println("Error TransformerConfigurationException: " + e.getMessage());
        } catch (TransformerFactoryConfigurationError e) {
            System.out.println("Error TransformerFactoryConfigurationError: " + e.getMessage());
        } catch (TransformerException e) {
            System.out.println("Error TransformerException: " + e.getMessage());
        }

    }
}
