import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    private static Connection db = Database.getInstance();

    public static void main(String[] args) throws Exception {

        try {
            Statement smt = db.createStatement();
            ResultSet rset = smt.executeQuery("SELECT * FROM proyecto");
            while (rset.next()) {
                System.out.println("Numproy: " + rset.getInt("numproy"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
