import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GestorLibreria {
 private static Connection db = Database.getInstance();
 public boolean berificarId(int id) {
        if (id <= 0) {
            System.out.println("El numero de proyecto debe ser un entero positivo. Introduce otro numero: ");
            return true;
        } else if (existeId(id)) {
            System.out.println("El numero de proyecto ya existe. Introduce otro numero: ");
            return true;
        }
        return false;
    }

    private boolean existeId(int id) {
        try {
            Statement smt = db.createStatement();
            ResultSet rset = smt.executeQuery("SELECT COUNT(*) AS count FROM objetos.libros WHERE id = " + id);
            if (rset.next()) {
                return rset.getInt("count") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
