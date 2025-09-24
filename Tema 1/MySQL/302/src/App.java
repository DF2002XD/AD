import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class App {
    private static Connection db = Database.getInstance();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int opcion = 0;
        do {
            menu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1:
                    listarProyecto();
                    break;
                case 2:
                    insertarProyecto();
                    break;

                case 3:
                    eliminarProyecto();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 4);

    }

    private static void eliminarProyecto() {
        System.out.println("Introduce el numero de proyecto a eliminar: ");
        int numproy = sc.nextInt();
        try {
            Statement smt = db.createStatement();
            int filasAfectadas = smt.executeUpdate("DELETE FROM proyecto WHERE numproy = " + numproy);
            if (filasAfectadas > 0) {
                System.out.println("Proyecto eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún proyecto con ese número.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertarProyecto() {
        try {
            Gestorproyecto gspro = new Gestorproyecto();

            System.out.println("Introduce el numero de proyecto: ");
            int numproy = sc.nextInt();
            while (gspro.berificarNumProyecto(numproy)) {
                numproy = sc.nextInt();
            }

            System.out.println("Introduce el nombre del proyecto: ");
            String nombreproy = sc.next();
            sc.nextLine();

            System.out.println("Introduce el lugar del proyecto: ");
            String lugarproy = sc.next();
            sc.nextLine();

            System.out.println("Introduce el numero de departamento: ");
            int numdep = sc.nextInt();
            sc.nextLine();

            Statement smt = db.createStatement();
            int filasAfectadas = smt
                    .executeUpdate("INSERT INTO proyecto (numproy, Nombreproy, Lugarproy, departamento_Numdep) VALUES ("
                            + numproy + ", '" + nombreproy + "', '" + lugarproy + "', " + numdep + ")");
            if (filasAfectadas > 0) {
                System.out.println("Proyecto insertado correctamente.");
            } else {
                System.out.println("Error al insertar el proyecto.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void listarProyecto() {
        try {
            Statement smt = db.createStatement();
            ResultSet rset = smt.executeQuery("SELECT * FROM proyecto");
            while (rset.next()) {
                System.out.println("Numproy: " + rset.getInt("numproy") + " Nombreproy: " + rset.getString("Nombreproy")
                        + " Lugarproy: " + rset.getString("Lugarproy") + " Departamento_Numdep: "
                        + rset.getInt("departamento_Numdep"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menu() {
        System.out.println("\n1. Listar proyecto");
        System.out.println("2. Insertar proyecto");
        System.out.println("3. Eliminar proyecto");
        System.out.println("4. Salir");
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
    public static String leerNombre() {
        while (true) {
            System.out.println("Introduzca su nombre (mínimo 3 caracteres, solo letras y espacios):");
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 3 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }
            
        }
    }

}
