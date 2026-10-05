package ud1.practicaex;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GenerarXmlJaxb {
    public static void main(String[] args) {
        List<String> listaEtiquetas = new ArrayList<>();
        listaEtiquetas.add("Etiqueta1");
        Libro l = new Libro("941-1", "Titulo1", "Autor1", "2026-05-23", 5.45, listaEtiquetas);
        List<Libro> libros = new ArrayList<>();
        libros.add(l);
        Biblioteca b = new Biblioteca("Biblioteca1", libros);
        
        
        try {
            JAXBContext contexto = JAXBContext.newInstance(Biblioteca.class);
            // Crear objeto que convierte Java → XML
            Marshaller marshaller = contexto.createMarshaller();
            // XML con formato legible
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Escribir en fichero
            marshaller.marshal(b, new File("bibliotecaJAXB.xml"));
            System.out.println("XML creado.");
        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
