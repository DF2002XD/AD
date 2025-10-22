import java.util.Scanner;

public class ValidarDatos {
    static final Scanner sc = new Scanner(System.in);


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

    public static String leerEmail() {
        while (true) {
            System.out.println("Introduzca su correo electrónico:");
            String email = sc.nextLine().trim();
            if (email.matches("^[\\w.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) {
                return email;
            } else {
                System.out.println("Correo electrónico inválido. Inténtelo de nuevo.");
            }
            
        }
    }

    public static String leerDni() {
        String dni;
        do {
            System.out.println("Introduzca su DNI (8 números + 1 letra):");
            dni = sc.nextLine().trim();
        } while (!validarDni(dni));
        return dni;
    }

    private static boolean validarDni(String dni) {
        if (!dni.matches("[0-9]{8}[A-Z]")) return false;

        // Cálculo de la letra correcta del DNI
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letraCorrecta = letras.charAt(numero % 23);
        return dni.charAt(8) == letraCorrecta;
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


    public static int enteroCorrecto(String dato, int minInclusive, int maxInclusive) {
        int num = 0;
        boolean datoOk = false;

        while (!datoOk) {
            System.out.println(dato);
            String input = sc.nextLine();
            try {
                num = Integer.parseInt(input);
                if (num >= minInclusive && num <= maxInclusive) {
                    datoOk = true;
                } else {
                    System.out.println("El valor debe estar entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException exception) {
                System.out.println("Debe introducir un número válido.");
            }
        }
        return num;
    }

    public static double doubleCorrecto(String dato, double minInclusive, double maxInclusive) {
        double num = 0;
        boolean datoOk = false;

        while (!datoOk) {
            System.out.println(dato);
            String input = sc.nextLine();
            try {
                num = Double.parseDouble(input);
                if (num >= minInclusive && num <= maxInclusive) {
                    datoOk = true;
                } else {
                    System.out.println("El valor debe estar entre " + minInclusive + " y " + maxInclusive);
                }
            } catch (NumberFormatException exception) {
                System.out.println("Debe introducir un número válido.");
            }
        }
        return num;
    }


    public static boolean leerBooleano(String dato) {
        boolean datoOk = false;
        String input = "";

        while (!datoOk) {
            System.out.println("Introduzca " + dato);
            System.out.println("S|s o N|n");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("N")) datoOk = true;
            else System.out.println("Introduzca S o N correctamente.");
        }
        return input.equalsIgnoreCase("S");
    }

    public static String stringCorrecto(String dato) {
        String input;
        do {
            System.out.println(dato);
            input = sc.nextLine();
        } while (input.isBlank());
        return input;
    }
}
