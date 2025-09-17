import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Agenda agenda = new Agenda();
        int opcion;

        do {
            System.out.println("\n--- Menú de la Agenda ---");
            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar contactos");
            System.out.println("3. Eliminar contacto");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    String nombre = leerNombre();
                    String telefono = leerTelefono();
                    Contacto nuevoContacto = new Contacto(nombre, telefono);
                    agenda.addContacto(nuevoContacto);
                    break;
                case 2:
                    agenda.mostrarContactos();
                    break;
                case 3:
                    System.out.print("Introduzca el nombre del contacto a eliminar: ");
                    agenda.mostrarContactos();
                    String nombreEliminar = sc.nextLine().trim();
                    agenda.eleminarContacto(nombreEliminar);
                    break;
                case 4:
                    System.out.println("Saliendo de la agenda. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);

        sc.close();
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

    public static String leerTelefono() {
        String telefono;
        do {
            System.out.println("Introduzca su número de teléfono (puede incluir código de país):");
            telefono = sc.nextLine().trim();
        } while (!validarTelefono(telefono));
        return telefono;
    }

    private static boolean validarTelefono(String telefono) {
        // Expresión regular para teléfonos nacionales e internacionales
        return telefono.matches("\\+?[0-9]{1,3}[ -]?\\d{9}") || telefono.matches("\\d{9}");
    }

}
