package ud1.practicaex;

import java.io.FileReader;

import com.google.gson.Gson;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import java.io.File;

public class ConversionJsonXmlJaxb {
    public static void main(String[] args) {
        try (FileReader reader = new FileReader("pedidos.json")) {
            Gson gson = new Gson();

            Tienda t = gson.fromJson(reader, Tienda.class);

            JAXBContext contexto = JAXBContext.newInstance(Tienda.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(t, new File("pedidosJaxb.xml"));
            System.out.println("XML creado.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
