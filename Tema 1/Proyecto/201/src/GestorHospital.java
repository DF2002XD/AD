import java.sql.Connection;
import java.sql.Date;
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

            String sql = "INSERT INTO hospital.medicos (nombre_medico, contacto) values (?,ROW(?,?,?,?))";
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
        try {
            String sql = "DELETE FROM hospital.medicos WHERE id_medico = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, id);

            int filasEliminadas = psmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Médico eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el médico. Verifique el ID proporcionado.");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearPaciente(String nombre, String email, LocalDate fechaNacimiento) {
        try {

            String sql = "INSERT INTO pacientes (nombre, email, fecha_Nacimiento) VALUES (?,?,?)";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            psmt.setString(1, nombre);
            psmt.setString(2, email);
            psmt.setDate(3, Date.valueOf(fechaNacimiento));
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

    public void eliminarPaciente(int id) {
        try {
            String sql = "DELETE FROM pacientes WHERE id_Paciente = ?";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            psmt.setInt(1, id);
            int filasEliminadas = psmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Paciente eliminado correctamente.");
            } else {
                System.out.println("No se pudo eliminar el paciente. Verifique el ID proporcionado.");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crearTratamiento(String nombre, String descripcion, String nombreEspecialidad, String nifMedico) {
        try {
            String sql = "INSERT INTO hospital.tratamientos ()";
           
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
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

    public void listarMedicos() {
        try {
            String sql = "SELECT id_medico, nombre_medico FROM hospital.medicos";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Médicos:");
            while (rset.next()) {
                int id = rset.getInt("id_medico");
                String nombre = rset.getString("nombre_medico");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarPacientes() {
        try {
            String sql = "SELECT id_Paciente, nombre FROM pacientes";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Pacientes:");
            while (rset.next()) {
                int id = rset.getInt("id_Paciente");
                String nombre = rset.getString("nombre");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public boolean existeNif(String dniMedico) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM hospital.medicos WHERE (contacto).nif = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setString(1, dniMedico);
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

    public boolean existeIdMedico(int idMedico) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM hospital.medicos WHERE id_medico = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, idMedico);
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

    public boolean existeIdPaciente(int idPaciente) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM pacientes WHERE id_Paciente = ?";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            psmt.setInt(1, idPaciente);
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

    public void listarEspecialidades() {
        try {
            String sql = "SELECT id_medico, nombre_medico, (contacto).nif FROM hospital.medicos";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Médicos:");
            while (rset.next()) {
                int id = rset.getInt("id_medico");
                String nombre = rset.getString("nombre_medico");
                String nif = rset.getString("nif");
                System.out.println("ID: " + id + ", Nombre: " + nombre + ", NIF: " + nif);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarMedicosNif() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarMedicosNif'");
    }

    public boolean existeIdEspecialidad(int idEspecialidad) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM hospital.especialidades WHERE id_especialidad = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, idEspecialidad);
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