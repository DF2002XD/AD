import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ConsultaNombres {
    private static Connection db = Database.getInstance();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        try {
            String letra = leerNombre();
            PreparedStatement psmt = db.prepareStatement("SELECT nombre FROM empleado WHERE nombre LIKE ?");
            psmt.setString(1, letra + "%");
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("Nombre"));
            }
            rs.close();
            psmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String leerNombre() {
        while (true) {
            System.out.println("Introduzca su nombre (mínimo 1 caracteres, solo letras y espacios):");
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 1 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }
            
        }
    }
}
