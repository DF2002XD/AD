package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.hibernate.Session;
import org.w3c.dom.events.Event;

import com.example.Entidades.Habilidad;
import com.example.Entidades.Participa;
import com.example.Entidades.Personaje;
import com.example.Repositorios.EventoRep;
import com.example.Repositorios.HabilidadRep;
import com.example.Repositorios.PersonajeRep;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final PersonajeRep personajeRep = new PersonajeRep();
    private static final HabilidadRep habilidadRep = new HabilidadRep();
    private static final EventoRep eventoRep = new EventoRep();

    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();
        System.out.println("Conexion a MySQL realizada con exito");

        int opcion;
        do {
            menu();
            opcion = leerEntero("Seleccione una opción (0 para salir): ");
            sc.nextLine(); // Limpiar buffer
            switch (opcion) {
                case 1:
                    crearPersonaje();
                    break;
                case 2:
                    borrarPersonajePorId();
                    break;
                case 3:
                    modificarPersonaje();
                    break;
                case 4:
                    crearHabilidad();
                    break;
                case 5:
                    borrarHabilidadPorNombre();
                    break;
                case 6:
                    modificarHabilidad();
                    break;
                case 7:
                    asignarHabilidadAPersonaje();
                    break;
                case 8:
                    registrarParticipacionEnEvento();
                    break;
                case 9:
                    cambiarTrajeDePersonaje();
                    break;
                case 10:
                    mostrarDatosDePersonaje();
                    break;
                case 11:
                    mostrarPersonajesEnEvento();
                    break;
                case 12:
                    mostrarCantidadPersonajesConHabilidad();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 0);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void mostrarCantidadPersonajesConHabilidad() {
        System.out.println("Mostrar cuántos personajes tienen una habilidad concreta...");
        habilidadRep.mostrarHabilidad();
        String nombre = leerNombre("Ingrese el nombre de la habilidad: ");
        habilidadRep.mostrarCantidadPersonajesConHabilidad(nombre);
    }

    private static void mostrarPersonajesEnEvento() {
        System.out.println("Mostrar personajes que participaron en un evento...");
        eventoRep.mostrarEventos();
        int id = leerEntero("Ingrese el ID del evento: ");
        personajeRep.mostrarPersonajesEnEvento(id);
    }

    private static void mostrarDatosDePersonaje() {
        System.out.println("Mostrar datos de un personaje...");
        personajeRep.mostrarPersonajes();
        int id = leerEntero("Ingrese el ID del personaje a mostrar: ");
        personajeRep.mostrarPersonajeCompleto(id);
    }

    private static void cambiarTrajeDePersonaje() {
        System.out.println("Cambiar traje de personaje...");
        personajeRep.mostrarPersonajes();
        String nombre = leerNombre("Ingrese el nombre del personaje: ");
        Personaje personaje = HibernateUtil.get().openSession().createQuery("FROM Personaje p WHERE p.nombre = :nombre", Personaje.class)
                .setParameter("nombre", nombre)
                .uniqueResult();
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
    }

    private static void registrarParticipacionEnEvento() {
        System.out.println("Registrar participación de personaje en evento...");
        personajeRep.mostrarPersonajes();
        String nombre = leerNombre("Ingrese el nombre del personaje: ");
        Personaje personaje = HibernateUtil.get().openSession().createQuery("FROM Personaje p WHERE p.nombre = :nombre", Personaje.class)
                .setParameter("nombre", nombre)
                .uniqueResult();
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        eventoRep.mostrarEventos();
        String eventoNombre = leerNombre("Ingrese el nombre del evento: ");
        Event evento = HibernateUtil.get().openSession().createQuery("FROM Evento e WHERE e.nombre = :nombre", Event.class)
                .setParameter("nombre", eventoNombre)
                .uniqueResult();
        if (evento == null) {
            System.out.println("Evento no encontrado.");
            return;
        }
        int Participaid = leerEntero("Ingrese el ID de la participación: ");
        Participa participa = new Participa();
        participa.setId(Participaid);
        participa.setPersonaje(personaje);
        participa.setEvento(evento);
        personaje.getListaparticipa().add(participa);
        evento.getListaparticipa().add(participa);
        personajeRep.modificar(personaje);
        eventoRep.modificar(evento);
        System.out.println("Participación registrada con éxito.");

    }

    private static void asignarHabilidadAPersonaje() {
        System.out.println("Asignar habilidad a personaje...");
        personajeRep.mostrarPersonajes();
        int personajeId = leerEntero("Ingrese el ID del personaje: ");
        Personaje personaje = HibernateUtil.get().openSession().get(Personaje.class, personajeId);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        habilidadRep.mostrarHabilidad();
        int habilidadId = leerEntero("Ingrese el ID de la habilidad: ");
        Habilidad habilidad = HibernateUtil.get().openSession().get(Habilidad.class, habilidadId);
        if (habilidad == null) {
            System.out.println("Habilidad no encontrada.");
            return;
        }
        personaje.addListahabilidad(habilidad);
        habilidad.addListapersonaje(personaje);
        personajeRep.modificar(personaje);
        habilidadRep.modificar(habilidad);
        System.out.println("Habilidad asignada con éxito.");
    }

    private static void modificarHabilidad() {
        System.out.println("Modificar habilidad...");
        habilidadRep.mostrarHabilidad();
        int id = leerEntero("Ingrese el ID de la habilidad a modificar: ");
        Habilidad habilidad = HibernateUtil.get().openSession().get(Habilidad.class, id);
        if (habilidad == null) {
            System.out.println("Habilidad no encontrada.");
            return;
        }
        String nuevoNombre = leerNombre("Nuevo nombre (actual: " + habilidad.getNombre() + "): ");
        String nuevaDescripcion = leerNombre("Nueva descripción (actual: " + habilidad.getDescripcion() + "): ");
        habilidad.setNombre(nuevoNombre);
        habilidad.setDescripcion(nuevaDescripcion);
        habilidadRep.modificar(habilidad);
        System.out.println("Habilidad modificada con éxito.");
    }

    private static void borrarHabilidadPorNombre() {
        habilidadRep.mostrarHabilidad();
        String nombre = leerNombre("Nombre de la habilidad a borrar: ");
        habilidadRep.borrarPorNombre(nombre);
        System.out.println("Habilidad borrada con éxito.");
    }

    private static void crearHabilidad() {
        System.out.println("Creando habilidad...");
        String nombre = leerNombre("Nombre de la habilidad: ");
        String descripcion = leerNombre("Descripción de la habilidad: ");

        habilidadRep.guardar(new Habilidad(nombre, descripcion));
    }

    private static void modificarPersonaje() {
        System.out.println("Modificar personaje...");
        personajeRep.mostrarPersonajes();
        int id = leerEntero("Ingrese el ID del personaje a modificar: ");
        Personaje personaje = HibernateUtil.get().openSession().get(Personaje.class, id);
        if (personaje == null) {
            System.out.println("Personaje no encontrado.");
            return;
        }
        String nuevoNombre = leerNombre("Nuevo nombre (actual: " + personaje.getNombre() + "): ");
        String nuevoAlias = leerNombre("Nuevo alias (actual: " + personaje.getAlias() + "): ");
        personaje.setNombre(nuevoNombre);
        personaje.setAlias(nuevoAlias);
        personajeRep.modificar(personaje);
        System.out.println("Personaje modificado con éxito.");
    }

    private static void borrarPersonajePorId() {
        personajeRep.mostrarPersonajes();
        int id = leerEntero("Ingrese el ID del personaje a borrar: ");
        personajeRep.borrarPorId(id);
        System.out.println("Personaje borrado con éxito.");
    }

    private static void crearPersonaje() {
        System.out.println("Creando personaje...");
        System.out.print("Nombre: ");
        String nombre = sc.next();
        sc.nextLine();
        System.out.print("Alias: ");
        String alias = sc.next();

        personajeRep.guardar(new Personaje(nombre, alias));
    }

    private static void menu() {
        System.out.println("Menú de opciones:");
        System.out.println("1. Crear personaje");
        System.out.println("2. Borrar personaje por ID");
        System.out.println("3. Modificar personaje");
        System.out.println("4. Crear habilidad");
        System.out.println("5. Borrar habilidad por nombre");
        System.out.println("6. Modificar habilidad");
        System.out.println("7. Asignar habilidad a personaje");
        System.out.println("8. Registrar participación de personaje en evento");
        System.out.println("9. Cambiar traje de personaje");
        System.out.println("10. Mostrar datos de un personaje");
        System.out.println("11. Mostrar personajes que participaron en un evento");
        System.out.println("12. Mostrar cuántos personajes tienen una habilidad concreta");
        System.out.println("0. Salir");
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

}
