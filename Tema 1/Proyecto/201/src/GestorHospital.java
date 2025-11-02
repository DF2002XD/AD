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
            String sqlIdEsp = "SELECT id_especialidad FROM hospital.especialidades WHERE nombre_especialidad = ?";
            PreparedStatement psmtIdEsp = dbpostgre.prepareStatement(sqlIdEsp);
            psmtIdEsp.setString(1, nombreEspecialidad);
            ResultSet rsetIdEsp = psmtIdEsp.executeQuery();

            int idEspecialidad = -1;
            if (rsetIdEsp.next()) {
                idEspecialidad = rsetIdEsp.getInt("id_especialidad");
            } else {
                System.out.println("No se encontró la especialidad con nombre: " + nombreEspecialidad);
                return;
            }
            rsetIdEsp.close();
            psmtIdEsp.close();

            String sqlIdMed = "SELECT id_medico FROM hospital.medicos WHERE (contacto).nif = ?";
            PreparedStatement psmtIdMed = dbpostgre.prepareStatement(sqlIdMed);
            psmtIdMed.setString(1, nifMedico);
            ResultSet rsetIdMed = psmtIdMed.executeQuery();

            int idMedico = -1;
            if (rsetIdMed.next()) {
                idMedico = rsetIdMed.getInt("id_medico");
            } else {
                System.out.println("No se encontró el médico con NIF: " + nifMedico);
                return;
            }
            rsetIdMed.close();
            psmtIdMed.close();

            String sqlMySQL = "INSERT INTO tratamientos (nombre_tratamiento, descripcion) VALUES (?, ?)";
            PreparedStatement psmtMySQL = dbmysql.prepareStatement(sqlMySQL, PreparedStatement.RETURN_GENERATED_KEYS);
            psmtMySQL.setString(1, nombre);
            psmtMySQL.setString(2, descripcion);
            psmtMySQL.executeUpdate();

            ResultSet rsetMySQL = psmtMySQL.getGeneratedKeys();
            int idTratamiento = -1;
            if (rsetMySQL.next()) {
                idTratamiento = rsetMySQL.getInt(1);
            } else {
                System.out.println("No se pudo obtener el ID generado en MySQL.");
                return;
            }
            rsetMySQL.close();
            psmtMySQL.close();

            String sqlPostgre = "INSERT INTO hospital.tratamientos (id_tratamiento, id_medico, id_especialidad) VALUES (?, ?, ?)";
            PreparedStatement psmtPost = dbpostgre.prepareStatement(sqlPostgre);
            psmtPost.setInt(1, idTratamiento);
            psmtPost.setInt(2, idMedico);
            psmtPost.setInt(3, idEspecialidad);

            int filasInsertadas = psmtPost.executeUpdate();
            psmtPost.close();

            if (filasInsertadas > 0) {
                System.out
                        .println("Tratamiento añadido correctamente en ambas bases de datos con ID: " + idTratamiento);
            } else {
                System.out.println("Insertado en MySQL pero falló en PostgreSQL.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarTratamientoPorNombre(String nombre) {
        try {
            String sqlBuscar = "SELECT id_tratamiento FROM tratamientos WHERE nombre_tratamiento = ?";
            PreparedStatement psBuscar = dbmysql.prepareStatement(sqlBuscar);
            psBuscar.setString(1, nombre);
            ResultSet rs = psBuscar.executeQuery();

            int idTratamiento = -1;
            if (rs.next()) {
                idTratamiento = rs.getInt("id_tratamiento");
            } else {
                System.out.println("No se encontró ningún tratamiento con el nombre: " + nombre);
                return;
            }
            psBuscar.close();

            String sqlDelPostgre = "DELETE FROM hospital.tratamientos WHERE id_tratamiento = ?";
            PreparedStatement psDelPostgre = dbpostgre.prepareStatement(sqlDelPostgre);
            psDelPostgre.setInt(1, idTratamiento);
            int filasPost = psDelPostgre.executeUpdate();
            psDelPostgre.close();

            String sqlDelMySQL = "DELETE FROM tratamientos WHERE id_tratamiento = ?";
            PreparedStatement psDelMySQL = dbmysql.prepareStatement(sqlDelMySQL);
            psDelMySQL.setInt(1, idTratamiento);
            int filasMy = psDelMySQL.executeUpdate();
            psDelMySQL.close();

            if (filasMy > 0 && filasPost > 0) {
                System.out.println("Tratamiento '" + nombre + "' eliminado correctamente de ambas bases de datos.");
            } else if (filasMy > 0) {
                System.out.println("Eliminado de MySQL pero no se encontró en PostgreSQL.");
            } else if (filasPost > 0) {
                System.out.println("Eliminado de PostgreSQL pero no se encontró en MySQL.");
            } else {
                System.out.println("No se pudo eliminar el tratamiento.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarTratamientosConPocosPacientes(int cantidad) {
        try {
            String sql = "SELECT t.nombre_tratamiento, COUNT(pt.id_Paciente) AS cantidad_pacientes FROM tratamientos t LEFT JOIN pacientes_tratamientos pt ON t.id_tratamiento = pt.id_tratamiento GROUP BY t.id_tratamiento HAVING COUNT(pt.id_Paciente) < ?";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            psmt.setInt(1, cantidad);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Tratamientos con menos de " + cantidad + " pacientes:");
            while (rset.next()) {
                String nombre = rset.getString("nombre_tratamiento");
                int cantPacientes = rset.getInt("cantidad_pacientes");
                System.out.println("Nombre: " + nombre + ", Cantidad de Pacientes: " + cantPacientes);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerCantidadTratamientosPorSala() {
        try {
            String sql = "SELECT id_sala, COUNT(id_tratamiento) AS cantidad_tratamientos FROM hospital.salas_tratamiento GROUP BY id_sala";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Cantidad de tratamientos por sala:");
            while (rset.next()) {
                int idSala = rset.getInt("id_sala");
                int cantidadTratamientos = rset.getInt("cantidad_tratamientos");
                System.out.println("ID Sala: " + idSala + ", Cantidad de Tratamientos: " + cantidadTratamientos);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerTotalCitasPorPaciente() {
        try {
            String sql = "SELECT id_Paciente, COUNT(id_cita) AS total_citas FROM citas GROUP BY id_Paciente";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Total de citas por paciente:");
            while (rset.next()) {
                int idPaciente = rset.getInt("id_Paciente");
                int totalCitas = rset.getInt("total_citas");
                System.out.println("ID Paciente: " + idPaciente + ", Total de Citas: " + totalCitas);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarTratamientosConEspecialidadYMedico() {
        try {
            
            String sqlPostgre = "SELECT t.id_tratamiento, m.nombre_medico AS nombre_medico, e.nombre_especialidad AS nombre_especialidad FROM hospital.tratamientos t JOIN hospital.medicos m ON t.id_medico = m.id_medico JOIN hospital.especialidades e ON t.id_especialidad = e.id_especialidad ORDER BY t.id_tratamiento;";
            
            PreparedStatement psmtPost = dbpostgre.prepareStatement(sqlPostgre);
            
            ResultSet rsetPost = psmtPost.executeQuery();
            while (rsetPost.next()) {
                int idTratamiento = rsetPost.getInt("id_tratamiento");
                String nombreMedico = rsetPost.getString("nombre_medico");
                String nombreEspecialidad = rsetPost.getString("nombre_especialidad");

                String sqlmySql = "SElECT nombre_tratamiento FROM tratamientos WHERE id_tratamiento = ?;";
                PreparedStatement psmt = dbmysql.prepareStatement(sqlmySql);
                psmt.setInt(1, idTratamiento);

                ResultSet rset = psmt.executeQuery();
                String nombreTratamiento = "Desconocido";
                if (rset.next()) {
                    nombreTratamiento = rset.getString("nombre_tratamiento");
                }
                rset.close();
                psmt.close();
                System.out.println("Nombre: " + nombreTratamiento + ", Médico: " + nombreMedico + ", Especialidad: " + nombreEspecialidad);

            }
            rsetPost.close();
            psmtPost.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void obtenerPacientesPorEspecialidad(int idEspecialidad) {
        try {
          String sqlPostgre = "SELECT id_tratamiento FROM hospital.tratamientos WHERE id_especialidad = ?";
            PreparedStatement psmtPost = dbpostgre.prepareStatement(sqlPostgre);
            psmtPost.setInt(1, idEspecialidad);

            ResultSet rsetPost = psmtPost.executeQuery();
            while (rsetPost.next()) {
                int idTratamiento = rsetPost.getInt("id_tratamiento");

                String sqlMySQL = "SELECT p.id_Paciente, p.nombre FROM pacientes p JOIN pacientes_tratamientos pt ON p.id_Paciente = pt.id_Paciente WHERE pt.id_tratamiento = ?";
                PreparedStatement psmtMySQL = dbmysql.prepareStatement(sqlMySQL);
                psmtMySQL.setInt(1, idTratamiento);

                ResultSet rsetMySQL = psmtMySQL.executeQuery();
                System.out.println("Pacientes para el tratamiento ID " + idTratamiento + ":");
                while (rsetMySQL.next()) {
                    int idPaciente = rsetMySQL.getInt("id_Paciente");
                    String nombrePaciente = rsetMySQL.getString("nombre");
                    System.out.println("ID Paciente: " + idPaciente + ", Nombre: " + nombrePaciente);
                }
                rsetMySQL.close();
                psmtMySQL.close();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            String sql = "SELECT id_especialidad, nombre_especialidad FROM hospital.especialidades";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Especialidades:");
            while (rset.next()) {
                int id = rset.getInt("id_especialidad");
                String nombre = rset.getString("nombre_especialidad");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarMedicosNif() {
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

    public void listarTratamientos() {
        try {
            String sql = "SELECT nombre_tratamiento FROM tratamientos";
            PreparedStatement psmt = dbmysql.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Tratamientos:");
            while (rset.next()) {
                String nombre = rset.getString("nombre_tratamiento");
                System.out.println("Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public String obtenerNombrePorIdEspecialidad(int idEspecialidad) {
        try {
            String sql = "SELECT nombre_especialidad FROM hospital.especialidades WHERE id_especialidad = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, idEspecialidad);
            ResultSet rset = psmt.executeQuery();
            if (rset.next()) {
                String nombre = rset.getString("nombre_especialidad");
                rset.close();
                psmt.close();
                return nombre;
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerNifPorIdMedico(int idMedico) {
        try {
            String sql = "SELECT (contacto).nif FROM hospital.medicos WHERE id_medico = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, idMedico);
            ResultSet rset = psmt.executeQuery();
            if (rset.next()) {
                String nif = rset.getString("nif");
                rset.close();
                psmt.close();
                return nif;
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}