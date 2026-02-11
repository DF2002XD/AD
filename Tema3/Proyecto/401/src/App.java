import java.util.InputMismatchException;
import java.util.Scanner;

import org.basex.examples.api.BaseXClient;
import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class App {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static Scanner sc = new Scanner(System.in);
    private static ObjectId currentClientId = null;

    public static void main(String[] args) throws Exception {
        int option;

        do {
            menu();
            option = leerEntero("Seleccione una opción: ");
            switch (option) {
                case 1:
                    modificarValorElementoXML();
                    break;
                case 2:
                    eliminarProductoXML();
                    break;
                case 3:
                    consulta1();
                    break;
                case 4:
                    consulta2();
                    break;
                case 5:
                    consulta3();
                    break;
                case 6:
                    consulta4();
                    break;
                case 7:
                    consulta5();
                    break;
                case 8:
                    crearCliente();
                    break;
                case 9:
                        identificarCliente();
                    break;
                case 10:
                    borrarCliente();
                    break;
                case 11:
                        modificarCampoCliente();
                    break;
                case 12:
                        anadirProductoCarrito();
                    break;
                case 13:
                        mostrarCarritoCliente();
                    break;
                case 14:
                        mostrarPedidosCliente();
                    break;
                case 15:
                            pagarCarritoCliente();
                    break;
                case 16:
                    consulta1Mongo();
                    break;
                case 17:
                    consulta2Mongo();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }

    private static void modificarValorElementoXML() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarValorElementoXML'");
    }

    private static void eliminarProductoXML() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarProductoXML'");
    }

    private static void consulta1() {
         BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "for $p in /productos/producto " +
                           "order by $p/nombre " +
                           "return concat('ID: ', $p/id, ' | Nombre: ', $p/nombre, ' | Precio: ', $p/precio, ' | Disp: ', $p/disponibilidad, ' | Cat: ', $p/categoria)";
            System.out.println(session.execute("XQUERY " + query));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void consulta2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta2'");
    }

    private static void consulta3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta3'");
    }

    private static void consulta4() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta4'");
    }

    private static void consulta5() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
    }

    private static void crearCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearCliente'");
    }

    private static void identificarCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'identificarCliente'");
    }

    private static void borrarCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'borrarCliente'");
    }

    private static void modificarCampoCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarCampoCliente'");
    }

    private static void anadirProductoCarrito() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'anadirProductoCarrito'");
    }

    private static void mostrarCarritoCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarCarritoCliente'");
    }

    private static void mostrarPedidosCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarPedidosCliente'");
    }

    private static void pagarCarritoCliente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pagarCarritoCliente'");
    }

    private static void consulta1Mongo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta1Mongo'");
    }

    private static void consulta2Mongo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta2Mongo'");
    }

    private static void menu() {
        System.out.println("-------Sobre la base de datos XML-------");
        System.out.println("1. Modificar el valor de un elemento de un XML según un ID.");
        System.out.println("2. Eliminar un producto según su ID.");
        System.out.println("3. Consulta 1: Obtener todos los productos por orden alfabético del nombre.");
        System.out.println("4. Consulta 2: Listar productos con una disponibilidad mayor a X unidades.");
        System.out.println(
                "5. Consulta 3: Mostrar la categoría, el nombre y el precio del producto más caro para cada categoría. En el caso de haber varios se devolverá el de la primera posición.");
        System.out.println(
                "6. Consulta 4: Mostrar el nombre de los productos y su fabricante para aquellos productos cuya descripción incluya una subcadena. Se deberá mostrar la información ordenada según el nombre del fabricante de forma inversa al alfabeto.");
        System.out.println(
                "7. Consulta 5: Mostrar la cantidad total de productos en cada categoría (teniendo en cuenta el elemento disponibilidad) y calcular el porcentaje que representa respecto al total de productos.");
        System.out.println("-------Sobre la base de datos MongoDB-------");

        System.out.println("8. Crear un nuevo cliente (no podrá haber email repetidos).");
        System.out.println(
                "9. Identificar cliente según el email. Dado el email se obtendrá el ID del cliente de forma que las siguientes consultas se harán sobre ese cliente. Para cambiar de cliente se tendrá que volver a seleccionar esta opción.");
        System.out.println("10. Borrar un cliente.");
        System.out.println("11. Modificar el valor de un campo de la información del cliente.");
        System.out.println(
                "12. Añadir producto al carrito del cliente. Se pedirá: id del producto y cantidad, así como si se desea seguir introduciendo más productos.");
        System.out.println("13. Mostrar el carrito del cliente. Se mostrarán los datos del carrito y el precio total.");
        System.out.println("14. Mostrar pedidos del cliente.");
        System.out.println(
                "15. Pagar el carrito de un cliente: se mostrará el carrito junto con una orden de confirmación. Si la orden es positiva se pasarán todos los productos a formar parte de un nuevo pedido.");
        System.out.println(
                "16. Consulta 1: Teniendo en cuenta todos los clientes, calcular el total de la compra para cada carrito y listar los resultados ordenados por el total de forma ascendente. (No es necesario tener en cuenta la multiplicación de precio_unitario * cantidad con sumar los precio_unitario es suficiente).");
        System.out.println(
                "17. Consulta 2: Teniendo en cuenta todos los clientes, obtener el total gastado por cada cliente en todos sus pedidos.");
        System.out.println("0. Salir");
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe introducir un número entero.");
                sc.nextLine(); // Limpiar buffer
            }
        }
    }
}
