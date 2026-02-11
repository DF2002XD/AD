import org.bson.types.ObjectId;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Projections;
import org.basex.examples.api.BaseXClient;
import org.bson.Document;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class pruebaxml {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static Scanner sc = new Scanner(System.in);
    private static ObjectId currentClientId = null;

    public static void main(String[] args) throws Exception {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("tienda");
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
                    mongoClient.close();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (option != 0);
    }


    private static void modificarValorElementoXML() {
        sc.nextLine();
        System.out.print("ID del producto: ");
        String id = sc.nextLine();
        System.out.print("Elemento a modificar (ej. precio): ");
        String elemento = sc.nextLine();
        System.out.print("Nuevo valor: ");
        String valor = sc.nextLine();

        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "replace value of node /productos/producto[id='" + id + "']/" + elemento + " with '" + valor + "'";
            session.execute("XQUERY " + query);
            System.out.println("Valor modificado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void eliminarProductoXML() {
        sc.nextLine();
        System.out.print("ID del producto a eliminar: ");
        String id = sc.nextLine();

        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "delete node /productos/producto[id='" + id + "']";
            session.execute("XQUERY " + query);
            System.out.println("Producto eliminado.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
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
        int x = leerEntero("Introduzca disponibilidad mínima: ");
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "for $p in /productos/producto " +
                           "where number($p/disponibilidad) > " + x + " " +
                           "return concat('ID: ', $p/id, ' | Nombre: ', $p/nombre, ' | Precio: ', $p/precio, ' | Disp: ', $p/disponibilidad, ' | Cat: ', $p/categoria)";
            System.out.println(session.execute("XQUERY " + query));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void consulta3() {
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "for $cat in distinct-values(/productos/producto/categoria) " +
                           "let $prods := /productos/producto[categoria=$cat] " +
                           "let $max := max($prods/precio) " +
                           "let $p := ($prods[precio=$max])[1] " +
                           "return concat('Categoría: ', $cat, ' | Producto: ', $p/nombre, ' | Precio: ', $p/precio)";
            System.out.println(session.execute("XQUERY " + query));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void consulta4() {
        sc.nextLine();
        System.out.print("Introduzca subcadena de descripción: ");
        String sub = sc.nextLine();
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "for $p in /productos/producto " +
                           "where contains($p/descripcion, '" + sub + "') " +
                           "order by $p/fabricante descending " +
                           "return concat('Producto: ', $p/nombre, ' | Fabricante: ', $p/fabricante)";
            System.out.println(session.execute("XQUERY " + query));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void consulta5() {
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "abc123");
            session.execute("OPEN productos");
            String query = "let $total := sum(/productos/producto/disponibilidad) " +
                           "for $cat in distinct-values(/productos/producto/categoria) " +
                           "let $sumCat := sum(/productos/producto[categoria=$cat]/disponibilidad) " +
                           "let $porc := ($sumCat div $total) * 100 " +
                           "return concat('Categoría: ', $cat, ' | Total: ', $sumCat, ' | Porcentaje: ', format-number($porc, '0.00'), '%')";
            System.out.println(session.execute("XQUERY " + query));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void crearCliente() {
        sc.nextLine();
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Dirección: ");
        String direccion = sc.nextLine();

        if (clientes.find(Filters.eq("email", email)).first() != null) {
            System.out.println("Error: Ya existe un cliente con ese email.");
            return;
        }
        clientes.insertOne(new Document("nombre", nombre).append("email", email).append("direccion", direccion));
        System.out.println("Cliente creado.");
    }

    private static void identificarCliente() {
        sc.nextLine();
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Introduce el email: ");
        String email = sc.nextLine();
        Document cliente = clientes.find(Filters.eq("email", email)).first();
        if (cliente != null) {
            currentClientId = cliente.getObjectId("_id");
            System.out.println("Cliente identificado: " + cliente.getString("nombre"));
        } else {
            System.out.println("Cliente no encontrado.");
            currentClientId = null;
        }
    }

    private static void borrarCliente() {
        sc.nextLine();
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Email del cliente a borrar: ");
        String email = sc.nextLine();
        Document deleted = clientes.findOneAndDelete(Filters.eq("email", email));
        if (deleted != null) {
            System.out.println("Cliente eliminado.");
            if (currentClientId != null && currentClientId.equals(deleted.getObjectId("_id"))) currentClientId = null;
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private static void modificarCampoCliente() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        sc.nextLine();
        System.out.println("Campo a modificar (nombre, email, direccion): ");
        String campo = sc.nextLine();
        System.out.print("Nuevo valor: ");
        String valor = sc.nextLine();
        database.getCollection("clientes").updateOne(Filters.eq("_id", currentClientId), Updates.set(campo, valor));
        System.out.println("Cliente actualizado.");
    }

    private static void anadirProductoCarrito() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        sc.nextLine();
        System.out.print("ID del producto: ");
        String idStr = sc.nextLine();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        
        Document prodInfo = getProductoBaseX(idStr);
        if (prodInfo != null) {
            Document item = new Document("producto_id", Integer.parseInt(idStr))
                    .append("nombre", prodInfo.getString("nombre"))
                    .append("cantidad", cantidad)
                    .append("precio_unitario", prodInfo.getDouble("precio"));
            MongoCollection<Document> carritos = database.getCollection("carritos");
            Document carrito = carritos.find(Filters.eq("cliente_id", currentClientId)).first();
            if (carrito == null) {
                carritos.insertOne(new Document("cliente_id", currentClientId).append("productos", Arrays.asList(item)));
            } else {
                carritos.updateOne(Filters.eq("_id", carrito.getObjectId("_id")), Updates.push("productos", item));
            }
            System.out.println("Producto añadido.");
        } else {
            System.out.println("Producto no encontrado en BaseX.");
        }
    }

    private static Document getProductoBaseX(String id) {
        BaseXClient session = null;
        try {
            session = new BaseXClient("localhost", 1984, "admin", "admin");
            session.execute("OPEN productos");
            String nombre = session.execute("XQUERY //producto[id='" + id + "']/nombre/text()");
            String precio = session.execute("XQUERY //producto[id='" + id + "']/precio/text()");
            if (nombre.isEmpty() || precio.isEmpty()) return null;
            return new Document("nombre", nombre).append("precio", Double.parseDouble(precio));
        } catch (Exception e) { return null; 
        } finally {
            if (session != null) try { session.close(); } catch (Exception e) {}
        }
    }

    private static void mostrarCarritoCliente() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        Document carrito = database.getCollection("carritos").find(Filters.eq("cliente_id", currentClientId)).first();
        if (carrito == null) { System.out.println("Carrito vacío."); return; }
        List<Document> prods = (List<Document>) carrito.get("productos");
        double total = 0;
        for (Document p : prods) {
            double sub = p.getDouble("precio_unitario") * p.getInteger("cantidad");
            total += sub;
            System.out.printf("%s (x%d) - %.2f\n", p.getString("nombre"), p.getInteger("cantidad"), sub);
        }
        System.out.printf("Total: %.2f\n", total);
    }

    private static void mostrarPedidosCliente() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        for (Document p : database.getCollection("pedidos").find(Filters.eq("cliente_id", currentClientId))) {
            System.out.println("Pedido " + p.getObjectId("_id") + " | Total: " + p.getDouble("total"));
        }
    }

    private static void pagarCarritoCliente() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        mostrarCarritoCliente();
        sc.nextLine();
        System.out.print("¿Confirmar pago? (s/n): ");
        if (sc.nextLine().equalsIgnoreCase("s")) {
            Document carrito = database.getCollection("carritos").findOneAndDelete(Filters.eq("cliente_id", currentClientId));
            if (carrito != null) {
                List<Document> prods = (List<Document>) carrito.get("productos");
                double total = 0;
                for (Document p : prods) total += p.getDouble("precio_unitario") * p.getInteger("cantidad");
                database.getCollection("pedidos").insertOne(new Document("cliente_id", currentClientId)
                        .append("productos", prods).append("total", total).append("fecha_pedido", new Date()));
                System.out.println("Pago realizado.");
            }
        }
    }

    private static void consulta1Mongo() {
        for (Document d : database.getCollection("carritos").aggregate(Arrays.asList(
            Aggregates.project(Projections.fields(Projections.include("cliente_id"), Projections.computed("total", new Document("$sum", "$productos.precio_unitario")))),
            Aggregates.sort(Sorts.ascending("total"))
        ))) System.out.println("Cliente: " + d.get("cliente_id") + " | Total: " + d.get("total"));
    }

    private static void consulta2Mongo() {
        for (Document d : database.getCollection("pedidos").aggregate(Arrays.asList(
            Aggregates.group("$cliente_id", Accumulators.sum("total_gastado", "$total"))
        ))) System.out.println("Cliente: " + d.get("_id") + " | Total Gastado: " + d.get("total_gastado"));
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
