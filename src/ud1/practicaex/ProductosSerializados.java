package ud1.practicaex;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductosSerializados {
    public static void main(String[] args) {
        List<Producto> productos = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("productos.dat"))) {
            int numRegistros = in.readInt();
            for (int i = 0; i < numRegistros; i++) {
                productos.add((Producto)in.readObject());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado. Creándolo...");
        } catch (IOException e) {
            System.out.println("Error de E/S: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("No se encuentra la clase: " + e.getMessage());
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("MENU");
        System.out.println("1. Añadir");
        System.out.println("2. Listar");
        System.out.println("3. Buscar");
        System.out.print("Elige una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Id: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Nombre:");
                String nombre = sc.nextLine();
                
                System.out.print("Precio: ");
                double precio = sc.nextDouble();
                Producto p = new Producto(id, nombre, precio);
                productos.add(p);

                try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("productos.dat"))) {
                    out.writeInt(productos.size());
                    for (Producto producto : productos) {
                        out.writeObject(producto);
                    }
                    System.out.println("Añadido correctamente");
                } catch (IOException e) {
                    System.out.println("Error de E/S: " + e.getMessage());
                }
                break;
            case 2:
                for (Producto producto : productos) {
                    System.out.println(producto);
                }
                break;
            case 3:
                System.out.print("Inserta el nombre del producto a buscar: ");
                String nombreProd = sc.nextLine();
                boolean encontrado = false;
                for (Producto producto : productos) {
                    if (producto.getNombre().equalsIgnoreCase(nombreProd)) {
                        System.out.println("Encontrado: " + producto.toString());
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("No se ha encontrado ningún producto con ese nombre");
                }
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }
}
