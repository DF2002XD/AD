
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static Connection db = Database.getInstance();
    private static GestorLibreria gl = new GestorLibreria();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int opcion = 0;
        do {
            menu();
            opcion = leerEntero("Elige una opcion: ");
            switch (opcion) {
                case 1:
                    insertarLibro();
                    break;
                case 2:
                    consultarLibros();
                    break;

                case 3:
                    actualizarLibro();
                    break;
                case 4:
                    eliminarLibro();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
            }
        } while (opcion != 5);
        sc.close();
        db.close();
        System.out.println("Conexión cerrada");
    }

    private static void eliminarLibro() {
        int id = leerEntero("Ingrese el ID del libro a eliminar: ");
        try {
            String sql = "DELETE FROM objetos.libros WHERE id = ?";
            PreparedStatement pssmt = db.prepareStatement(sql);
            pssmt.setInt(1, id);
            int filasEliminadas = pssmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("El libro se ha eliminado correctamente.");
            } else {
                System.out.println("No se encontró un libro con ese ID.");
            }
            pssmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void actualizarLibro() {
        int id = leerEntero("Ingrese el ID del libro a actualizar: ");
        try {
            int 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertarLibro() {
        try {
            int id = leerEntero("ID del libro: ");
            while (gl.berificarId(id)) {
                id = leerEntero("ID del libro: ");
            }
            sc.nextLine(); // Limpiar buffer
            String titulo = leerNombre("Titulo del libro: ");
            String nombreAutor = leerNombre("Nombre del autor: ");
          
            int fechaAutor = leerEntero("Fecha del autor: ");
       
            int añoPublicacion = leerEntero("Año de publicación: ");

            String sql = String.format(
                    "INSERT INTO objetos.libros (id, titulo, autor, año_publicacion) VALUES (?, ?, ROW(?, ?), ?)");
            PreparedStatement pssmt = db.prepareStatement(sql);
            pssmt.setInt(1, id);
            pssmt.setString(2, titulo);
            pssmt.setString(3, nombreAutor);
            pssmt.setInt(4, fechaAutor);
            pssmt.setInt(5, añoPublicacion);
            int filasInsertadas = pssmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("El libro se ha insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el libro.");
            }
            pssmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void consultarLibros() {
        String consulta = leerNombre("Buscar libros por titulo o autor: ");
        try {
            String sql = "SELect * FROM objetos.libros WHERE titulo LIKE ? OR(autor).nombre_autor LIKE ?";
            PreparedStatement pssmt = db.prepareStatement(sql);
            pssmt.setString(1, "%" + consulta + "%");
            pssmt.setString(2, "%" + consulta + "%");
            ResultSet rset = pssmt.executeQuery();
            while (rset.next()) {
                System.out.println("ID: " + rset.getInt("id"));
                System.out.println("Titulo: " + rset.getString("titulo"));
                System.out.println("Autor: " + rset.getString("autor"));
                System.out.println("Año de publicación: " + rset.getInt("año_publicacion"));
                System.out.println("---------------------------");
            }
            rset.close();
            pssmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void menu() {
        System.out.println("\n1. Insertar libro");
        System.out.println("2. Consulta libros");
        System.out.println("3. Actualizar libro");
        System.out.println("4. Eliminar libro");
        System.out.println("5. Salir");
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

    public static String leerNombre(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            try {
                String nombre = sc.nextLine();
                if (nombre.length() >= 3 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+")) {
                    return nombre;
                } else {
                    System.out.println("Error: El nombre debe tener al menos 3 caracteres y solo contener letras y espacios.");
                }
            } catch (Exception e) {
                System.out.println("Error al leer el dato. Inténtalo de nuevo.");
                sc.nextLine(); // Limpiar buffer
            }
        }

    }
}
