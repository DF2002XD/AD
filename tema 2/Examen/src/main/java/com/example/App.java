package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.hibernate.Session;

import com.example.Entidades.Ciclo;
import com.example.Entidades.Instituto;
import com.example.Repositorios.CicloRep;
import com.example.Repositorios.InstitutoRep;

public class App {
    private static final Scanner sc = new Scanner(System.in);
    private static final InstitutoRep institutoRep = new InstitutoRep();
    private static final CicloRep cicloRep = new CicloRep();

    public static void main(String[] args) {
        Session session = HibernateUtil.get().openSession();
        System.out.println("Iniciando la conexion a MySQL");

        int option;
        do {
            menu();
            option = leerEntero("Seleccione una opcion (0 para salir): ");
            sc.nextLine();
            switch (option) {
                case 1:
                    crearIntituto();
                    break;
                case 2:
                    eliminarIntituto();
                    break;
                case 3:
                    crearCiclo();
                    break;
                case 4:
                    eliminarCiclo();
                    break;
                case 5:
                    modificarTelefonoIntituto();
                    break;
                case 6:
                    asignarDirectorIntituto();
                    break;
                case 7:
                    asignarCicloTaller();
                    break;
                case 8:
                    consulta1();
                    break;
                case 9:
                    consulta2();
                    break;
                case 10:
                    consulta3();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        } while (option != 0);


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }

    private static void crearIntituto() {
        System.out.println("Crear Instituto");
        String nombre = leerNombre("Introduce el nombre del instituto: ");
        String tf = leerNombre("Introduce el telefono del instituto: ");
        institutoRep.guardar(new Instituto(nombre, tf));
        System.out.println("Instituto creado correctamente");
    }

    private static void eliminarIntituto() {
       System.out.println("Eliminar Instituto");
       institutoRep.mostrar();
        int id = leerEntero("Introduce el id del instituto a eliminar: ");
        institutoRep.eliminar(id);
        System.out.println("Instituto eliminado correctamente");
    }

    private static void crearCiclo() {
        System.out.println("Crear ciclo");
        String nombreCiclo = leerNombre("Introduce el nombre del ciclo: ");
        cicloRep.guardar(new Ciclo(nombreCiclo));
        System.out.println("Ciclo creado correctamente");
    }

    private static void eliminarCiclo() {
        System.out.println("Eliminar ciclo");
       cicloRep.mostrar();
        int id = leerEntero("Introduce el id del ciclo a eliminar: ");
        cicloRep.eliminar(id);
        System.out.println("Ciclo eliminado correctamente");
    }

    private static void modificarTelefonoIntituto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modificarTelefonoIntituto'");
    }

    private static void asignarDirectorIntituto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarDirectorIntituto'");
    }

    private static void asignarCicloTaller() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'asignarCicloTaller'");
    }

    private static void consulta1() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta1'");
    }

    private static void consulta2() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta2'");
    }

    private static void consulta3() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'consulta3'");
    }

    private static void menu() {
        System.out.println("1. Crear Instituto");
        System.out.println("2. Eliminar Instituto");
        System.out.println("3. Crear Ciclo");
        System.out.println("4. Eliminar Ciclo");
        System.out.println("5. Modificar Teléfono Instituto");
        System.out.println("6. Asignar Director Instituto");
        System.out.println("7. Asignar Ciclo Taller");
        System.out.println("8. Consulta 1");
        System.out.println("9. Consulta 2");
        System.out.println("10. Consulta 3");
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
            if (!nombre.isEmpty()) {
                return nombre;
            } else {
                System.out.println("Nombre inválido. Inténtelo de nuevo.");
            }
        }
    }
}
