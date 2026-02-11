import org.basex.BaseXClient;
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
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class prueba {
  
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static Scanner scanner = new Scanner(System.in);
    private static ObjectId currentClientId = null;

    public static void main(String[] args) throws Exception {
        // Conexión a MongoDB
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("tienda");

        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- MENÚ ---");
            System.out.println("Cliente identificado: " + (currentClientId == null ? "Ninguno" : currentClientId.toString()));
            System.out.println("1. Crear un nuevo cliente");
            System.out.println("2. Identificar cliente (Login por email)");
            System.out.println("3. Borrar un cliente");
            System.out.println("4. Modificar cliente");
            System.out.println("5. Añadir producto al carrito");
            System.out.println("6. Mostrar carrito");
            System.out.println("7. Mostrar pedidos del cliente");
            System.out.println("8. Pagar carrito");
            System.out.println("9. Consulta 1: Total carritos (ascendente)");
            System.out.println("10. Consulta 2: Total gastado por cliente");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1": crearCliente(); break;
                case "2": identificarCliente(); break;
                case "3": borrarCliente(); break;
                case "4": modificarCliente(); break;
                case "5": anadirProductoCarrito(); break;
                case "6": mostrarCarrito(); break;
                case "7": mostrarPedidos(); break;
                case "8": pagarCarrito(); break;
                case "9": consulta1(); break;
                case "10": consulta2(); break;
                case "0": exit = true; break;
                default: System.out.println("Opción no válida.");
            }
        }
        mongoClient.close();
    }

    private static void crearCliente() {
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String direccion = scanner.nextLine();

        if (clientes.find(Filters.eq("email", email)).first() != null) {
            System.out.println("Error: Ya existe un cliente con ese email.");
            return;
        }

        Document cliente = new Document("nombre", nombre)
                .append("email", email)
                .append("direccion", direccion);
        clientes.insertOne(cliente);
        System.out.println("Cliente creado correctamente.");
    }

    private static void identificarCliente() {
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Introduce el email: ");
        String email = scanner.nextLine();
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
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.print("Introduce el email del cliente a borrar: ");
        String email = scanner.nextLine();
        Document deleted = clientes.findOneAndDelete(Filters.eq("email", email));
        
        if (deleted != null) {
            System.out.println("Cliente eliminado.");
            if (currentClientId != null && currentClientId.equals(deleted.getObjectId("_id"))) {
                currentClientId = null;
            }
        } else {
            System.out.println("No se encontró el cliente.");
        }
    }

    private static void modificarCliente() {
        if (currentClientId == null) {
            System.out.println("Debes identificar un cliente primero.");
            return;
        }
        MongoCollection<Document> clientes = database.getCollection("clientes");
        System.out.println("¿Qué campo deseas modificar? (nombre, email, direccion)");
        String campo = scanner.nextLine();
        
        if (!campo.equals("nombre") && !campo.equals("email") && !campo.equals("direccion")) {
            System.out.println("Campo no válido.");
            return;
        }

        System.out.print("Nuevo valor: ");
        String valor = scanner.nextLine();

        if (campo.equals("email")) {
            // Verificar duplicados excluyendo el propio cliente
            if (clientes.find(Filters.and(Filters.eq("email", valor), Filters.ne("_id", currentClientId))).first() != null) {
                System.out.println("Error: El email ya está en uso.");
                return;
            }
        }

        clientes.updateOne(Filters.eq("_id", currentClientId), Updates.set(campo, valor));
        System.out.println("Cliente actualizado.");
    }

    private static void anadirProductoCarrito() {
        if (currentClientId == null) {
            System.out.println("Debes identificar un cliente primero.");
            return;
        }

        boolean continuar = true;
        while (continuar) {
            System.out.print("ID del producto: ");
            String idStr = scanner.nextLine();
            System.out.print("Cantidad: ");
            int cantidad = Integer.parseInt(scanner.nextLine());

            // Obtener datos de BaseX
            Document prodInfo = getProductoBaseX(idStr);
            if (prodInfo == null) {
                System.out.println("Producto no encontrado en BaseX.");
            } else {
                MongoCollection<Document> carritos = database.getCollection("carritos");
                Document item = new Document("producto_id", Integer.parseInt(idStr))
                        .append("nombre", prodInfo.getString("nombre"))
                        .append("cantidad", cantidad)
                        .append("precio_unitario", prodInfo.getDouble("precio"));

                Document carrito = carritos.find(Filters.eq("cliente_id", currentClientId)).first();
                if (carrito == null) {
                    carrito = new Document("cliente_id", currentClientId)
                            .append("productos", Arrays.asList(item));
                    carritos.insertOne(carrito);
                } else {
                    carritos.updateOne(Filters.eq("_id", carrito.getObjectId("_id")), Updates.push("productos", item));
                }
                System.out.println("Producto añadido: " + prodInfo.getString("nombre"));
            }

            System.out.print("¿Añadir otro producto? (s/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
    }

    private static Document getProductoBaseX(String id) {
        try (BaseXClient session = new BaseXClient("localhost", 1984, "admin", "admin")) {
            session.execute("OPEN productos"); // Asumimos BD 'productos'
            String nombre = session.execute("XQUERY //producto[id='" + id + "']/nombre/text()");
            String precio = session.execute("XQUERY //producto[id='" + id + "']/precio/text()");
            
            if (nombre.isEmpty() || precio.isEmpty()) return null;
            return new Document("nombre", nombre).append("precio", Double.parseDouble(precio));
        } catch (Exception e) {
            System.err.println("Error BaseX: " + e.getMessage());
            return null;
        }
    }

    private static void mostrarCarrito() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        MongoCollection<Document> carritos = database.getCollection("carritos");
        Document carrito = carritos.find(Filters.eq("cliente_id", currentClientId)).first();
        if (carrito == null) { System.out.println("Carrito vacío."); return; }

        List<Document> prods = (List<Document>) carrito.get("productos");
        double total = 0;
        System.out.println("--- Carrito ---");
        for (Document p : prods) {
            double sub = p.getDouble("precio_unitario") * p.getInteger("cantidad");
            total += sub;
            System.out.printf("%s (x%d) - %.2f\n", p.getString("nombre"), p.getInteger("cantidad"), sub);
        }
        System.out.printf("Total Carrito: %.2f\n", total);
    }

    private static void mostrarPedidos() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        MongoCollection<Document> pedidos = database.getCollection("pedidos");
        for (Document p : pedidos.find(Filters.eq("cliente_id", currentClientId))) {
            System.out.println("Pedido " + p.getObjectId("_id") + " | Fecha: " + p.getDate("fecha_pedido") + " | Total: " + p.getDouble("total"));
        }
    }

    private static void pagarCarrito() {
        if (currentClientId == null) { System.out.println("Identifícate primero."); return; }
        mostrarCarrito();
        MongoCollection<Document> carritos = database.getCollection("carritos");
        Document carrito = carritos.find(Filters.eq("cliente_id", currentClientId)).first();
        if (carrito == null) return;

        System.out.print("¿Confirmar pago? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            List<Document> prods = (List<Document>) carrito.get("productos");
            double total = 0;
            for (Document p : prods) total += p.getDouble("precio_unitario") * p.getInteger("cantidad");

            Document pedido = new Document("cliente_id", currentClientId)
                    .append("productos", prods)
                    .append("total", total)
                    .append("fecha_pedido", new Date());
            
            database.getCollection("pedidos").insertOne(pedido);
            carritos.deleteOne(Filters.eq("_id", carrito.getObjectId("_id")));
            System.out.println("Pago realizado. Pedido creado.");
        }
    }

    private static void consulta1() {
        MongoCollection<Document> carritos = database.getCollection("carritos");
        List<Bson> pipeline = Arrays.asList(
            Aggregates.project(Projections.fields(
                Projections.include("cliente_id"),
                Projections.computed("total_simple", new Document("$sum", "$productos.precio_unitario"))
            )),
            Aggregates.sort(Sorts.ascending("total_simple"))
        );
        for (Document d : carritos.aggregate(pipeline)) {
            System.out.println("Cliente: " + d.get("cliente_id") + " | Total Simple: " + d.get("total_simple"));
        }
    }

    private static void consulta2() {
        MongoCollection<Document> pedidos = database.getCollection("pedidos");
        List<Bson> pipeline = Arrays.asList(
            Aggregates.group("$cliente_id", Accumulators.sum("total_gastado", "$total"))
        );
        for (Document d : pedidos.aggregate(pipeline)) {
            System.out.println("Cliente: " + d.get("_id") + " | Total Gastado: " + d.get("total_gastado"));
        }
    }
}

