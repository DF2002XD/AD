import java.sql.Connection;
import java.time.LocalDate;

public class GestorHospital {
    private static Connection sql = DatabaseSQL.getInstance();
    private static Connection postgre = DatabasePostgre.getInstance();

    private static void crearEspecialidad(String nombreEspecialidad){
        // Lógica para crear una nueva especialidad médica
    }

    private static void crearMedico(String nombreMedico, String nif, int telefono, String email) {
        // Lógica para crear un nuevo médico
    }

    private static void eliminarMedico(int id){
        // Lógica para eliminar un médico por ID
    }

    private static void crearPaciente(String nombre, String email, LocalDate fechaNacimiento){
        // Lógica para crear un nuevo paciente
    }

    private static void eliminarPaciente(int id){
        // Lógica para eliminar un paciente por ID
    }

    private static void crearTratamiento(String nombre, String descripcion, String nombreEspecialidad, String nifMedico){
        // Lógica para crear un nuevo tratamiento
    }

    private static void eliminarTratamientoPorNombre(String nombre){
        // Lógica para eliminar un tratamiento por su nombre
    }

    private static void listarTratamientosConPocosPacientes(int cantidad){
        // Lógica para listar tratamientos con menos de 'cantidad' pacientes
    }

    private static void obtenerTotalCitasPorPaciente(){
        // Lógica para obtener el total de citas realizadas por cada paciente
    }

    private static void obtenerCantidadTratamientosPorSala(){
        // Lógica para obtener la cantidad de tratamientos por sala
    }

    private static void listarTratamientosConEspecialidadYMedico(){
        // Lógica para listar todos los tratamientos con sus respectivas especialidades y médicos
    }

    private static void obtenerPacientesPorEspecialidad(int idEspecialidad){
        // Lógica para obtener todos los pacientes que han recibido un tratamiento de una especialidad
    }
}
