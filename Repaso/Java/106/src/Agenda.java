import java.util.ArrayList;

public class Agenda {
    private static ArrayList<Contacto> contactos = new ArrayList<>();

    public void addContacto(Contacto contacto) {
        if (contacto != null) {
            contactos.add(contacto);
            System.out.println("Contacto añadido correctamente: " + contacto.getNombre() + " - " + contacto.getTelefono());
        } else {
            System.out.println("No se pudo añadir el contacto. El objeto es nulo.");
        }
    }

    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }

        System.out.println("Contactos en la agenda:");
        for (Contacto c : contactos) {
            System.out.println("Nombre: " + c.getNombre() + ", Teléfono: " + c.getTelefono());
        }
    }

    public void eleminarContacto(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("El nombre proporcionado es nulo o vacío.");
            return;
        }

        for (int i = 0; i < contactos.size(); i++) {
            if (contactos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                contactos.remove(i);
                System.out.println("Contacto eliminado: " + nombre);
                return;
            }
        }

        System.out.println("No se encontró ningún contacto con el nombre: " + nombre);
    }
}
