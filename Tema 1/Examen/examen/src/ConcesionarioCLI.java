import java.util.Scanner;

public class ConcesionarioCLI {
    private static Scanner sc = new Scanner(System.in);
    private static GestionConcesionario gesConce = new GestionConcesionario();

    public static void main(String[] args) {
        int opcion = 0;
        do {
            menu();
            opcion = pedirInt("Introduzca el número: ");
            sc.nextLine();
            switch (opcion) {
                case 1:
                    gesConce.nuevoSchema();
                    break;
                case 2:
                    gesConce.nuevoType();
                    break;
                case 3:
                    InsertaVenta();
                    break;
                case 4:
                    actualizarCoche();
                    break;
                case 5:
                    eliminarCoche();
                    break;
                case 6:
                    gesConce.consulta1();
                    break;
                case 7:
                    gesConce.consulta2();
                    break;
                case 8:
                    gesConce.consulta3();
                    break;
                case 9:
                    gesConce.consulta4();
                    break;
                case 10:
                    gesConce.consulta5();
                    break;
                case 11:
                    gesConce.consulta6();
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                default:
                    System.out.println("Seleccion erronea. Vuelva a meter el número"); 
                    break;
            }
        } while (opcion != 0);
    }

    private static void InsertaVenta() {
        gesConce.listarClientes();
        int cliente = pedirInt("Ingrese el id del cliente: ");
        while (!gesConce.existeIdCliente(cliente)) {
            System.out.println("El ID del  no existe. Introduzca otro ID:");
            cliente = pedirInt("Introduzca el ID del  a actualizar: ");
        }
        gesConce.listarCoches();
        int coche = pedirInt("Ingrese el id del coche: ");

        while (!gesConce.existeIdCoche(coche)) {
            System.out.println("El ID del  no existe. Introduzca otro ID:");
            coche = pedirInt("Introduzca el ID del  a actualizar: ");
        }

        String fecha = pedirString("Ingrese la fecha: ");

        int precio = pedirInt("Ingrese el precio: ");

        gesConce.insertVentas(cliente, coche, fecha, precio);
    }

    private static void actualizarCoche() {
        gesConce.listarCoches();
        int coche = pedirInt("Ingrese el id del coche: ");
        while (!gesConce.existeIdCoche(coche)) {
            System.out.println("El ID del  no existe. Introduzca otro ID:");
            coche = pedirInt("Introduzca el ID del coche a actualizar: ");
        }
        String modelo = pedirString("Introduzca el nuevo modelo: ");
        int anho = pedirInt("Introduzca el nuevo año: ");
        
        gesConce.listarVendedor("Introduzca el ID del vendedor a actualizar: ");
        int vendedor = pedirInt(modelo);

        gesConce.actuCoche(coche, modelo, anho, vendedor);
    }

    private static void eliminarCoche() {
        gesConce.listarCoches();
        int coche = pedirInt("Ingrese el id del coche: ");
        gesConce.elimCoche(coche);
    }

    public static void menu() {
        System.out.println("\n1. Crear esquema desguace");
        System.out.println("2. Crear tipo Componente");
        System.out.println("3. Insertar nueva venta");
        System.out.println("4. Actualizar la información de un coche");
        System.out.println("5. Eliminar la información de un coche");
        System.out.println("6. Consulta 1");
        System.out.println("7. Consulta 2");
        System.out.println("8. Consulta 3");
        System.out.println("9. Consulta 4");
        System.out.println("10. Consulta 5");
        System.out.println("0. Salir");
    }

    public static String pedirString(String mensaje) {
        System.out.println(mensaje);
        return sc.next();
    }

    public static int pedirInt(String mensaje) {
        while (true) {
            try {
                System.out.println(mensaje);
                return sc.nextInt();
            } catch (Exception e) {
            }
        }
    }
}