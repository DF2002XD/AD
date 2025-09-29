import java.sql.Connection;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BorradoEmpleados {
    private static Connection db = Database.getInstance();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            int num = leerEntero("Introduzca el número de empleados a borrar: ");
            //
            
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
