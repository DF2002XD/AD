import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabasePostgre{

    private static Connection connection;
    private final String usuario = "postgres";
    private final String clave = "abc123.";
    private final String url ="jdbc:postgresql://localhost:5432/hospital_postgre";

    // El constructor del singleton siempre debe ser privado para evitar llamadas de construcción directas con el operador `new`.
    private DatabasePostgre(){
        try {
            DatabasePostgre.connection = DriverManager.getConnection(url, usuario, clave);
            System.out.println("Conexión abierta postgre");
        }catch(SQLException sqle) {
            System.out.println("Error al abrir la conexión");
        }
    }

    // El método estático que controla el acceso a la instancia
    // singleton.
    public static Connection getInstance(){
        if (DatabasePostgre.connection == null) 
            new DatabasePostgre();
        return DatabasePostgre.connection;
    }
}
