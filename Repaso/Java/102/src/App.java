import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);
    private static int edadjubilacion = 65;

    public static void main(String[] args) throws Exception {

        String nombre = leerString("Introduzca el nombre: ");
        int edad = leerEntero("Introduzca la edad: ");

        if (edad < 18){
            System.out.println("Es menor de edad");
        }
        else if(edad < edadjubilacion) {
            System.out.println(nombre + " edad de jubilación = 65 años le quedan "+ Integer.toString(edadjubilacion -edad) + " para la jubilación");
        }else{
            System.out.println(nombre + " ya está jubilad@, lleva estos añs jubilad@ " + Integer.toString(edad -edadjubilacion));
        }

    }

    private static String leerString(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = sc.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            } else {
                System.out.println("Error: El texto no puede estar vacío.");
            }
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
