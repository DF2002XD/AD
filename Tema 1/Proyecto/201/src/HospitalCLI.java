import java.time.LocalDate;
import java.util.Date;
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
            sc.nextLine();
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
                    // Lógica para listar todos los tratamientos con sus respectivas especialidades
                    // y médicos
                    listarTrataConEspeYMed();
                    break;
                case 12:
                    // Lógica para obtener todos los pacientes que han recibido un tratamiento de
                    // una especialidad
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

        boolean salir = leerBooleano("¿Desea salir sin añadir una especialidad?");

        if (salir)
            return;

        String nombre = leerNombre(
                "Introduzca el nombre de la especialidad (mínimo 3 caracteres, solo letras y espacios):");
        while (hospital.existeEspecialidad(nombre)) {
            System.out.println("La especialidad ya existe. Introduzca otro nombre:");
            nombre = leerNombre(
                    "Introduzca el nombre de la especialidad (mínimo 3 caracteres, solo letras y espacios):");
        }
        hospital.crearEspecialidad(nombre);
    }

    private static void anadirMedico() {
        boolean salir = leerBooleano("¿Desea salir sin añadir un Medico?");

        if (salir)
            return;
        String nombreMedico = leerNombre(
                "Introduzca el nombre del medico (mínimo 3 caracteres, solo letras y espacios):");
        String dniMedico = leerDni();
        while (hospital.existeNif(dniMedico)) {
            System.out.println("El DNI ya existe. Introduzca otro DNI");
            dniMedico = leerDni();
        }
        int telefonoMedico = leerTelefono();
        String emailMedico = leerEmail();
        hospital.crearMedico(nombreMedico, dniMedico, telefonoMedico, emailMedico);
    }

    private static void eliminarMedico() {
        boolean salir = leerBooleano("¿Desea salir sin eleminar un Medico?");

        if (salir)
            return;
        hospital.listarMedicos();
        int idMedico = leerEntero("Introduzca el ID del médico a eliminar: ");
        while (!hospital.existeIdMedico(idMedico)) {
            System.out.println("El ID del médico no existe. Introduzca otro ID:");
            idMedico = leerEntero("Introduzca el ID del médico a eliminar: ");
        }
        hospital.eliminarMedico(idMedico);
    }

    private static void anadirPaciente() {
        boolean salir = leerBooleano("¿Desea salir sin añadir un Paciente?");
        if (salir)
            return;
        String nombre = leerNombre("Introduzca el nombre del paciente (mínimo 3 caracteres, solo letras y espacios):");
        String email = leerEmail();
        LocalDate fechaNacimiento = leerFechaNacimiento();
        hospital.crearPaciente(nombre, email, fechaNacimiento);
    }

    private static void eliminarPaciente() {
        boolean salir = leerBooleano("¿Desea salir sin eleminar un Paciente?");

        if (salir)
            return;
        hospital.listarPacientes();
        int idPaciente = leerEntero("Introduzca el ID del paciente a eliminar: ");
        while (!hospital.existeIdPaciente(idPaciente)) {
            System.out.println("El ID del médico no existe. Introduzca otro ID:");
            idPaciente = leerEntero("Introduzca el ID del médico a eliminar: ");
        }
        hospital.eliminarPaciente(idPaciente);
    }

    private static void anadirTratamiento() {
        String nombreTratamiento = leerNombre("Introduzca el nombre del tratamiento (mínimo 3 caracteres, solo letras y espacios):");
        String descripcionTratamiento = leerNombre( "Introduzca la descripción del tratamiento (mínimo 3 caracteres, solo letras y espacios):");

        hospital.listarEspecialidades();
        int idEspecialidad = leerEntero("Introduzca el ID de la especialidad asociada al tratamiento: ");
        while (!hospital.existeIdEspecialidad(idEspecialidad)) {
            System.out.println("El ID de la especialidad no existe. Introduzca otro ID:");
            idEspecialidad = leerEntero("Introduzca el ID de la especialidad asociada al tratamiento: ");
        }
        String nombreEspecialidad = hospital.obtenerNombrePorIdEspecialidad(idEspecialidad);

        hospital.listarMedicosNif();
        int idMedico = leerEntero("Introduzca el ID del médico asociado al tratamiento: ");
        while (!hospital.existeIdMedico(idMedico)) {
            System.out.println("El ID del médico no existe. Introduzca otro ID:");
            idMedico = leerEntero("Introduzca el ID del médico asociado al tratamiento: ");
        }
        String nifMedico = hospital.obtenerNifPorIdMedico(idMedico);

        hospital.crearTratamiento(nombreTratamiento, descripcionTratamiento, nombreEspecialidad, nifMedico);
    }

    private static void eliminarTratamiento() {
        String tratamiento = leerNombre("\"Introduzca el nombre del tratamiento (mínimo 3 caracteres, solo letras y espacios):");
        hospital.eliminarTratamientoPorNombre(tratamiento);
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

    public static boolean leerBooleano(String mensaje) {
        System.out.println(mensaje + " (s/n): ");
        String input = sc.nextLine().trim().toLowerCase();
        while (!input.equalsIgnoreCase("S") && !input.equalsIgnoreCase("N")) {
            System.out.println("Introduzca S o N correctamente.");
            input = sc.nextLine().trim().toLowerCase();
        }
        return input.equalsIgnoreCase("S");
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
}
