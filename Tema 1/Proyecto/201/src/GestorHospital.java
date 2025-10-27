import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class GestorHospital {
    private static Connection dbmysql = DatabaseSQL.getInstance();
    private static Connection dbpostgre = DatabasePostgre.getInstance();

    public void crearEspecialidad(String nombreEspecialidad) {
        try {
            String sql = "INSERT INTO hospital.especialidades (nombre_especialidad) VALUES (?)";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setString(1, nombreEspecialidad);
            int filasInsertadas = psmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Especialidad añadida correctamente.");
            } else {
                System.out.println("No se pudo añadir la especialidad.");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearMedico(String nombreMedico, String nif, int telefono, String email) {
        try {
            String sql = "INSERT INTO hospilat.medicos (nonmbre_medico, contacto) values (?,ROW(?,?,?,?))";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setString(1, "Dr. " + nombreMedico);
            psmt.setString(2, nif);
            psmt.setString(3, nombreMedico);
            psmt.setInt(4, telefono);
            psmt.setString(5, email);

            int filasInsertadas = psmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Medico añadido correctamente.");
            } else {
                System.out.println("No se pudo añadir el medico.");
            }
            psmt.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMedico(int id) {
        // Lógica para eliminar un médico por ID
    }

    public void crearPaciente(String nombre, String email, LocalDate fechaNacimiento) {
        // Lógica para crear un nuevo paciente
    }

    public void eliminarPaciente(int id) {
        // Lógica para eliminar un paciente por ID
    }

    public void crearTratamiento(String nombre, String descripcion, String nombreEspecialidad,
            String nifMedico) {
        // Lógica para crear un nuevo tratamiento
    }

    public void eliminarTratamientoPorNombre(String nombre) {
        // Lógica para eliminar un tratamiento por su nombre
    }

    public void listarTratamientosConPocosPacientes(int cantidad) {
        // Lógica para listar tratamientos con menos de 'cantidad' pacientes
    }

    public void obtenerTotalCitasPorPaciente() {
        // Lógica para obtener el total de citas realizadas por cada paciente
    }

    public void obtenerCantidadTratamientosPorSala() {
        // Lógica para obtener la cantidad de tratamientos por sala
    }

    public void listarTratamientosConEspecialidadYMedico() {
        // Lógica para listar todos los tratamientos con sus respectivas especialidades
        // y médicos
    }

    public void obtenerPacientesPorEspecialidad(int idEspecialidad) {
        // Lógica para obtener todos los pacientes que han recibido un tratamiento de
        // una especialidad
    }

    public boolean existeEspecialidad(String nombre) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM hospital.especialidades WHERE nombre_especialidad = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setString(1, nombre);
            ResultSet rset = psmt.executeQuery();
            if (rset.next()) {
                int count = rset.getInt("count");
                rset.close();
                psmt.close();
                return count > 0;
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    
}