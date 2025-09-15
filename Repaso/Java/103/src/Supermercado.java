import java.util.ArrayList;
import java.util.List;

public class Supermercado {
    private static List<Productos> productos = new ArrayList<>();

    public void addPersonal(Productos productos2) {
        if (productos2 != null) {
            productos.add(productos2);
            System.out.println("Personal añadido correctamente: " + productos2.getNombre());
        } else {
            System.out.println("No se pudo añadir el personal. El objeto es nulo.");
        }

    }

    static void mostrarProductos() {
        System.out.println("\nProductos comprados:");
        for (Productos producto : Supermercado.productos) {
            System.out.println("Nombre: " + producto.getNombre() + ", Precio: " + producto.getPrecio());
        }
        int totalPrecio = 0;
        for (Productos producto : Supermercado.productos) {
            totalPrecio += producto.getPrecio();
        }

        System.err.println("Total de productos: " + Supermercado.productos.size() + " pecio total: " + totalPrecio);
    }

}
