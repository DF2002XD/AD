import java.util.Scanner;

public class ValidarDatos {
    static final Scanner sc = new Scanner(System.in);

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

    private static String leerNombre(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 3 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }
        }
    }

    //Validar DNI
    public static String leerDni() {
        String dni;
        boolean valido = false;
        do {
            System.out.print("Introduzca los 8 dígitos del DNI: ");
            dni = sc.nextLine().trim();

            if (validarSoloNumeros(dni)) {
                valido = true;
            } else {
                System.out.println("⚠ El DNI debe tener exactamente 8 números. Intente de nuevo.");
            }
        } while (!valido);
        return agregarLetra(dni);
    }

    private static boolean validarSoloNumeros(String dni) {
        return dni.matches("\\d{8}");
    }

    private static String agregarLetra(String dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni);
        int resto = numero % 23;
        char letraCorrecta = letras.charAt(resto);
        return dni + letraCorrecta;
    }

    public static int leerTelefono() {
        int telefono = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print("Introduzca su número de teléfono (solo números): ");
            String input = sc.next().trim();
            if (input.matches("\\d{9}")) {
                try {
                    telefono = Integer.parseInt(input);
                    valido = true;
                } catch (NumberFormatException e) {
                    System.out.println("Número demasiado grande, int no lo puede manejar.");
                }
            } else {
                System.out.println("El teléfono debe contener exactamente 9 dígitos. Intente de nuevo.");
            }
        }
        return telefono;
    }

    public static String leerEmail() {
        while (true) {
            sc.nextLine();
            System.out.println("Introduzca su correo electrónico:");
            String email = sc.nextLine().trim();
            if (email.matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                return email;
            } else {
                System.out.println("Correo electrónico inválido. Inténtelo de nuevo.");
            }
        }
    }

    public static LocalDate leerFechaNacimiento() {
        while (true) {
            try {
                System.out.print("Introduzca la fecha de nacimiento (YYYY/MM/DD): ");
                String fechaInput = sc.nextLine().trim();
                String[] partes = fechaInput.split("/");
                int anio = Integer.parseInt(partes[0]);
                int mes = Integer.parseInt(partes[1]);
                int dia = Integer.parseInt(partes[2]);
                return LocalDate.of(anio, mes, dia);
            } catch (Exception e) {
                System.out.println("Fecha inválida. Inténtelo de nuevo.");
            }
        }
    }

    public static boolean leerBooleano(String mensaje) {
        System.out.println(mensaje + " (s/n): ");
        String input = sc.nextLine().trim().toLowerCase();
        while (!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("N")) {
            System.out.println("Introduzca S o N correctamente.");
            input = sc.nextLine().trim().toLowerCase();
        }
        return input.equalsIgnoreCase("S");
    }
}
