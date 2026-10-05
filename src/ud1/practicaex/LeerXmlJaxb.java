package ud1.practicaex;

import java.io.File;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

public class LeerXmlJaxb {
    public static void main(String[] args) {
        try {
            JAXBContext contexto = JAXBContext.newInstance(Biblioteca.class);

            Unmarshaller unmarshaller = contexto.createUnmarshaller();

            Biblioteca b = (Biblioteca) unmarshaller.unmarshal(new File("bibliotecaJAXB.xml"));
            List<Libro> libros = b.getLibros();
            
            System.out.println("Biblioteca: " + b.getNombre());
            for (Libro l : libros) {
                System.out.println("Titulo: " + l.getTitulo());
                System.out.println("Autor: " + l.getAutor());
                System.out.println("Fecha: " + l.getFechaPublicacion());
            }

        } catch (JAXBException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
