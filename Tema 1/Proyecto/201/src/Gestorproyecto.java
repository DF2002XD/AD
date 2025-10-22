import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Gestorproyecto {
    private static Connection dbsql = DatabaseSQL.getInstance();
    private static Connection dbpostgre = DatabasePostgre.getInstance();

    public boolean berificarNumProyecto(int numproy) {
        if (numproy <= 0) {
            System.out.println("El numero de proyecto debe ser un entero positivo. Introduce otro numero: ");
            return true;
        } else if (existeNumProy(numproy)) {
            System.out.println("El numero de proyecto ya existe. Introduce otro numero: ");
            return true;
        }
        return false;
    }

    private boolean existeNumProy(int numproy) {
        try {
            Statement smt = dbsql.createStatement();
            ResultSet rset = smt.executeQuery("SELECT COUNT(*) AS count FROM proyecto WHERE numproy = " + numproy);
            if (rset.next()) {
                return rset.getInt("count") > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
