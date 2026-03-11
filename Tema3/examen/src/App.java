import java.util.InputMismatchException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.basex.examples.api.BaseXClient;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;

public class App {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        mongoClient = MongoClients.create("mongodb://localhost:27017");
        database = mongoClient.getDatabase("peliculas");
        int option;

        do {
            menu();
            option = leerEntero("Seleccione una opción: ");
            switch (option) {
                case 1:
                    consulta1();
                    break;
                case 2:
                    consulta2();
                    break;
                case 3:
                    consulta3();
                    break;
                case 4:
                    consulta4();
                    break;
                case 5:
                    eliminarLibro();
                    break;
                case 6:
                    consulta1Mongo();
                    break;
                case 7:
                    consulta2Mongo();
                    break;
                case 8:
                    consulta3Mongo();
                    break;
                case 9:
                    consulta4Mongo();
                    break;
                case 10:
                    eliminarPelicula();
                    break;
                case 11:
                    anhadirDoc();
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

    private static void consulta1Mongo() {
        for (Document doc : database.getCollection("peliculas").find(gt("criticas.puntuacion", 4.5))
                .projection(fields(include("titulo"), excludeId()))) {
            System.out.println(doc.toJson());
        }
    }

    private static void consulta2Mongo() {

        for (Document doc : database.getCollection("peliculas")
                .find(and(eq("genero", "Drama"), eq("director", "Christopher Nolan")))) {
            System.out.println(doc.toJson());
        }
    }

    private static void consulta3Mongo() {
        for (Document doc : database.getCollection("peliculas")
                .find(and(gt("duracion_minutos", 120), eq("clasificacion", "R")))
                .projection(fields(include("titulo", "director"), excludeId()))) {
            System.out.println(doc.toJson());
        }
    }

    private static void consulta4Mongo() {
        for (Document doc : database.getCollection("peliculas").find(eq("genero", "Ciencia ficción"))) {
            List<Document> actores = doc.getList("actores", Document.class);
            int numActores = (actores != null) ? actores.size() : 0;

            System.out.println("Título: " + doc.getString("titulo") +
                    ", Año: " + doc.get("año") +
                    ", Número de actores: " + numActores);
        }
    }

    private static void eliminarPelicula() {
        MongoCollection<Document> collection = database.getCollection("peliculas");

        DeleteResult result = collection.deleteMany(and(
                lt("año", 1990),
                gt("duracion_minutos", 120),
                lt("criticas.puntuacion", 3.5)));

        System.out.println(result.getDeletedCount() + " película(s) eliminada(s).");
    }

    private static void anhadirDoc() {
        Document nuevoDoc = new Document("titulo", "Cadena perpetua")
                .append("director", "Frank Darabont")
                .append("año", 1994)
                .append("duracion_minutos", 142)
                .append("actores", Arrays.asList(
                        new Document("nombre", "Tim Robbins").append("personaje", "Andy Dufresne").append("edad", 36),
                        new Document("nombre", "Morgan Freeman").append("personaje", "Red").append("nacionalidad",
                                "Estadounidense")));

        database.getCollection("peliculas").insertOne(nuevoDoc);
        System.out.println("Documento insertado correctamente.");
    }

    private static void consulta1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta2'");
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
        throw new UnsupportedOperationException("Unimplemented method 'consulta3'");
    }

    private static void eliminarLibro() {
        BaseXClient sesion = null;
        try {
            sesion = new BaseXClient("localhost", 1984, "admin", "abc123");
            sesion.execute("OPEN biblioteca");
            String xquery = "for $p in  //libro[año_publicacion < 1900] return delete node $p";
            sesion.execute("XQUERY " + xquery);
            System.out.println("Libros eliminados inferior a 1900.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            try {
                if (sesion != null) {
                    sesion.close();
                }
            } catch (Exception e) {
                System.err.println("Error al cerrar la sesión BaseX: " + e.getMessage());
            }
        }
    }

    private static void menu() {
        System.out.println("-------Sobre la base de datos XML-------");
        System.out.println("1. Consulta 1: Obtener el título del libro con mayor cantidad de páginas.");
        System.out.println(
                "2. Consulta 2: Obtener el título de los libros ordenados por su puntuación de valoración de forma ascendente");
        System.out.println(
                "3. Consulta 3: Obtener la media de las puntuaciones que ha dado el usuario7 en libros de más de 300 páginas.");
        System.out.println(
                "4. Consulta 4: Obtener el nombre de los autores y el número de libros publicados por cada autor.");
        System.out.println("5.Elimina todos los libros cuyo año de publicación sea inferior a 1900.");

        System.out.println("-------Sobre la base de datos MongoDB-------");
        System.out.println("6. Consulta 1: Obtener todas las películas con una puntuación de críticas superior a 4.5.");
        System.out.println(
                "7. Consulta 2: Obtener todas las películas del género Drama dirigidas por Christopher Nolan.");
        System.out.println(
                "8. Consulta 3: Obtener el título y el director de las películas con una duración superior a 120 minutos y una clasificación R.");
        System.out.println(
                "9. Consulta 4: Obtener el título, el año de lanzamiento y el número de actores para cada película del género Ciencia ficción.");
        System.out.println(
                "10. Eliminar todas las películas lanzadas antes de 1990, que tengan una duración superior a 120 minutos pero que tengan una puntuación inferior a 3.5.");
        System.out.println(
                "11Añadir un nuevo documento, siguiendo la estructura de los campos de los documentos anteriores");
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
