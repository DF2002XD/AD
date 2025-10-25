import java.util.InputMismatchException;
import java.util.Scanner;

public class HospitalCLI {

    private static Scanner sc = new Scanner(System.in);
    private static final GestorHospital hospital = new GestorHospital();

    public static void main(String[] args) throws Exception {
        int opcion = 0;
        do {
            mostrarMenu();
            opcion = leerEntero("Seleccione una opción (0 para salir): ");
            switch (opcion) {
                case 1:
                    anadirEspecialidad();
                    break;
                case 2:
                    anadirMedico();
                    break;
                case 3:
                    eliminarMedico();
                    break;
                case 4:
                    // Lógica para crear un nuevo paciente 
                    anadirPaciente();
                    break;
                case 5:
                    // Lógica para eliminar un paciente
                    eliminarPaciente();
                    break;
                case 6:
                    // Lógica para crear nuevo tratamiento
                    anadirTratamiento();
                    break;
                case 7:
                    // Lógica para eliminar un tratamiento por su nombre
                    eliminarTratamiento();
                    break;
                case 8:
                    // Lógica para listar tratamientos
                    listarTratamientos();
                    break;
                case 9:
                    // Lógica para obtener el total de citas realizadas por cada paciente
                    obTotalCitXPaci();
                    break;
                case 10:
                    // Lógica para obtener la cantidad de tratamientos por sala
                    obrCantTrataXSala();
                    break;
                case 11:
                    // Lógica para listar todos los tratamientos con sus respectivas especialidades y médicos
                    listarTrataConEspeYMed();
                    break;
                case 12:
                    // Lógica para obtener todos los pacientes que han recibido un tratamiento de una especialidad
                    obPaciXEspe();
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

    }

    private static void anadirEspecialidad() {
        String nombre = leerNombre();
        while (hospital.existeEspecialidad(nombre) == true) {
            System.out.println("La especialidad ya existe. Introduzca otro nombre:");
            nombre = leerNombre();  
        }
        hospital.crearEspecialidad(nombre);
    }

    private static void anadirMedico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'anadirMedico'");
    }

    private static void eliminarMedico() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarMedico'");
    }

    private static void anadirPaciente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'anadirPaciente'");
    }

    private static void eliminarPaciente() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPaciente'");
    }

    private static void anadirTratamiento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'anadirTratamiento'");
    }

    private static void eliminarTratamiento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarTratamiento'");
    }

    private static void listarTratamientos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTratamientos'");
    }

    private static void obTotalCitXPaci() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obTotalCitXPaci'");
    }

    private static void obrCantTrataXSala() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obrCantTrataXSala'");
    }

    private static void listarTrataConEspeYMed() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarTrataConEspeYMed'");
    }

    private static void obPaciXEspe() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'obPaciXEspe'");
    }

    private static void mostrarMenu() {
        System.out.println("\nMenú de opciones:");
        System.out.println("1. Crear una nueva especialidad médica");
        System.out.println("2. Crear un nuevo médico ");
        System.out.println("3. Eliminar un médico por ID");
        System.out.println("4. Crear un nuevo paciente");
        System.out.println("5. Eliminar un paciente");
        System.out.println("6. Crear nuevo tratamiento");
        System.out.println("7. Eliminar un tratamiento por su nombre");
        System.out.println("8. Listar tratamientos");
        System.out.println("9. Obtener el total de citas realizadas por cada paciente");
        System.out.println("10. Obtener la cantidad de tratamientos por sala");
        System.out.println("11. Listar todos los tratamientos con sus respectivas especalidades y médicos");
        System.out.println("12. Obtener todos los pacientes que han recibido un tratamiento de una especialidad");
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

    private static String leerNombre() {
        while (true) {
            System.out.println("Introduzca su nombre (mínimo 3 caracteres, solo letras y espacios):");
            sc.nextLine();
            String nombre = sc.nextLine().trim();
            if (nombre.length() >= 3 && nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }

        }
    }
}
