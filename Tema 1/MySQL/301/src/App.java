import java.sql.Connection;

public class App {
    public static void main(String[] args) throws Exception {
        
        Connection db = Database.getInstance();
        if (db != null) {
            System.out.println("Conexión abierta");
        } else {
            System.out.println("No se ha podido abrir la conexión");
            
        }
    }
}
