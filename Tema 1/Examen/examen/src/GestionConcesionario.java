import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GestionConcesionario {
    private static Connection dbpostgre = DatabasePostgre.getInstance();

    public void nuevoSchema() {
        try {
            String sql = "CREATE SCHEMA desguace";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.executeQuery();
            System.out.println("Schema creada existosamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se creo el Schema");
        }
    }

    public void nuevoType() {
        try {
            String sql = "CREATE TYPE dessguace.Componente AS{Nombre VARCHAR(50), Precio DOUBLE, Tamaño INT}";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.executeQuery();
            System.out.println("Type creada existosamente");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("No se creo el Type");
        }

    }

    public void insertVentas(int cliente, int coche, String fecha, int precio) {
        try {
            String sql = "INSERT INTO ventas (cliente_id,coche_id,fecha_venta,precio_venta) VALUES (?,?,?,?)";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, cliente);
            psmt.setInt(2, coche);
            psmt.setString(3, fecha);
            psmt.setInt(4, precio);

            int filasEliminadas = psmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Venta creada exitosamente");
            } else {
                System.out.println("No se creo la venta");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void elimCoche(int coche) {
        try {
            String sqlDelVentas = "DELETE FROM concesionario.ventas WHERE coche_id = ?";
            PreparedStatement psDelVentas = dbpostgre.prepareStatement(sqlDelVentas);
            psDelVentas.setInt(1, coche);
            int deletedVentas = psDelVentas.executeUpdate();

            String sqlDelClientes = "DELETE FROM concesionario.clientes WHERE coche_preferido_id = ?";
            PreparedStatement psDelClientes = dbpostgre.prepareStatement(sqlDelClientes);
            psDelClientes.setInt(1, coche);
            int deletedClientes = psDelClientes.executeUpdate();

            String sqlDel = "DELETE FROM concesionario.coches WHERE coche_id = ?";
            PreparedStatement psDel = dbpostgre.prepareStatement(sqlDel);
            psDel.setInt(1, coche);
            int filasEliminadas = psDel.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println(
                        "Coche eliminado correctamente. Filas relacionadas eliminadas: clientes="
                                + deletedClientes + ", ventas=" + deletedVentas);
            } else {
                System.out.println("No se pudo eliminar el coche. Verifique el ID proporcionado.");
            }

            psDelVentas.close();
            psDelClientes.close();
            psDel.close();
        } catch (Exception e) {
            try {
                dbpostgre.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public void actuCoche(int coche, String modelo, int anho, int vendedor) {
        try {
            String sql = "UPDATE concesionario.coches SET ((detalles_coche), vendedor_id) VALUE (ROW(?,?),?) WHERE coche_id= ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setString(1, modelo);
            psmt.setInt(2, anho);
            psmt.setInt(3, vendedor);
            psmt.setInt(4, coche);
            int filasEliminadas = psmt.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Coche actualizado");
            } else {
                System.out.println("No se actualizo el coche");
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consulta1() {
        try {
            // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consulta2() {
        try {
            String sql = "SELECT (datos_personales) FROM concesionario.vendedores";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            while (rset.next()) {
                String nombre = rset.getString("cedula");
                String especialidad = rset.getString("especialidad");
                System.out.println("Nombre: " + nombre + ", Especialidad: " + especialidad);
            }
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consulta3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
    }

    public void consulta4() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
    }

    public void consulta5() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
    }

    public void consulta6() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta5'");
    }

    public boolean existeIdCoche(int coche) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM concesionario.coches WHERE coche_id = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, coche);
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

    public boolean existeIdCliente(int cliente) {
        try {
            String sql = "SELECT COUNT(*) AS count FROM concesionario.clientes WHERE cliente_id = ?";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            psmt.setInt(1, cliente);
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

    public void listarCoches() {
        try {
            String sql = "SELECT coche_id, detalles_coche FROM concesionario.coches";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de coches:");
            while (rset.next()) {
                int id = rset.getInt("coche_id");
                String nombre = rset.getString("detalles_coche");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarClientes() {
        try {
            String sql = "SELECT cliente_id, datos_personales FROM concesionario.clientes";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Clientes:");
            while (rset.next()) {
                int id = rset.getInt("cliente_id");
                String nombre = rset.getString("datos_personales");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listarVendedor(String string) {
        try {
            String sql = "SELECT vendedor_id, datos_personales FROM concesionario.vendedor";
            PreparedStatement psmt = dbpostgre.prepareStatement(sql);
            ResultSet rset = psmt.executeQuery();
            System.out.println("Lista de Clientes:");
            while (rset.next()) {
                int id = rset.getInt("vendedor_id");
                String nombre = rset.getString("datos_personales");
                System.out.println("ID: " + id + ", Nombre: " + nombre);
            }
            rset.close();
            psmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
