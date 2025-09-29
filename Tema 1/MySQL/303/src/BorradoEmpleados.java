import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.smartcardio.CardTerminals.State;

public class BorradoEmpleados {
    private static Connection db = Database.getInstance();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int num = leerEntero("Introduzca el número de empleados a borrar: ");
            
            Statement smt = db.createStatement();
            smt.execute("SET FOREIGN_KEY_CHECKS=0");

            PreparedStatement psmt = db.prepareStatement("DELETE FROM empleado WHERE nss= ?");
            psmt.setInt(1, num);

            int filasAfectadas = psmt.executeUpdate();
            System.out.println("Número de empleados borrados: " + filasAfectadas);

            psmt.close();
            smt.execute("SET FOREIGN_KEY_CHECKS=1");
            smt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
