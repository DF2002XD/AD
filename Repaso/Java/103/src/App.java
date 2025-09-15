import java.util.Scanner;

public class App {
    private static Scanner sc = new Scanner(System.in);
    private static Supermercado supermercado = new Supermercado();

    public static void main(String[] args) throws Exception {
        while (true) {
            String producto = leerNombre();
            int precio = enteroCorrecto("Introduzca el precio del producto", 0, 1000);
            boolean continuar = leerBooleano("si desea continuar");
            Productos prod = new Productos(producto, precio);
            supermercado.addPersonal(prod);
            if (!continuar)
                break;
            
        }
        Supermercado.mostrarProductos();
        sc.close();
    }

   

    public static String leerNombre() {
        while (true) {
            System.out.println("Introduzca el producto (mínimo 3 caracteres, solo letras y espacios):");
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 3 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }

        }
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

    public static boolean leerBooleano(String dato) {
        boolean datoOk = false;
        String input = "";

        while (!datoOk) {
            System.out.println("Introduzca " + dato);
            System.out.println("S|s o N|n");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("S") || input.equalsIgnoreCase("N"))
                datoOk = true;
            else
                System.out.println("Introduzca S o N correctamente.");
        }
        return input.equalsIgnoreCase("S");
    }

}