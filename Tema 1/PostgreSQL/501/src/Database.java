import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;
    private final String usuario = "postgres";
    private final String clave = "abc123.";
    private final String url = "jdbc:postgresql://localhost:5432/listalibros";

    // El constructor del singleton siempre debe ser privado para evitar llamadas de
    // construcción directas con el operador `new`.
    private Database() {
        try {
            Database.connection = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión abierta");
        } catch (SQLException sqle) {
            System.out.println("Error al abrir la conexión");

            try {
                String urlSinDB = "jdbc:postgresql://localhost:5432/";
                Connection connSinDB = java.sql.DriverManager.getConnection(urlSinDB, usuario, clave);
                Statement smt = connSinDB.createStatement();
                smt.executeUpdate("CREATE DATABASE listaLibros");
                smt.close();
                connSinDB.close();
                System.out.println("Base de datos creada correctamente.");
                // Ahora conectar de nuevo
                connection = java.sql.DriverManager.getConnection(url, usuario, clave);
                Statement smt1 = connection.createStatement();
                smt1.executeUpdate("CREATE SCHEMA IF NOT EXISTS objetos");
                smt1.executeUpdate(
                        "CREATE TYPE Autor AS (" +
                                "nombre_autor VARCHAR, " +
                                "fecha VARCHAR" +
                                ")");
                smt1.executeUpdate(
                        "CREATE TABLE objetos.libros (" +
                                "id SERIAL PRIMARY KEY, " +
                                "titulo TEXT, " +
                                "autor Autor, " +
                                "año_publicacion INTEGER" +
                                ")");
                smt1.close();
                System.out.println("Base de datos, tipo y tabla creados correctamente.");

            } catch (Exception e) {
                System.out.println("Error creando la base de datos, tipo o tabla");
                e.printStackTrace();
            }
        }
    }

    public static Connection getInstance() {
        if (Database.connection == null)
            new Database();
        return Database.connection;
    }
}