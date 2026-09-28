package ud1.ejemplos;

import java.io.*;
import java.net.URI;
import java.net.URL;


public class EjemploLeerURL {
   public static void main(String[] args) throws Exception {
       System.setProperty("java.net.useSystemProxies", "true");
       URI uri = new URI("https://data.iana.org/TLD/tlds-alpha-by-domain.txt");
       URL url = uri.toURL();
       InputStream is = url.openStream();
       InputStreamReader isr = new InputStreamReader(is);
       // es un puente de bytes a caracteres.
       int c;
       while ((c = isr.read()) != -1) {
           System.out.print((char) c);
       }
    }
}
