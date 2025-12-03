package com.example;

import org.hibernate.Session;

import com.example.Entidades.Dpto;
import com.example.Entidades.Emp;
import com.example.Repositorios.DptoRep;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        DptoRep dptoRep = new DptoRep();
        Dpto dpto1 = new Dpto("Finanzas", "Madrid");
        Dpto dpto2 = new Dpto("Recursos Humanos", "Barcelona");
        Dpto dpto3 = new Dpto("IT", "Valencia");


        Emp emp1 = new Emp("Juan", "Developer", 3000, 30, "12345678A", false);
        Emp emp2 = new Emp("Maria", "Manager", 5000, 40, "87654321B", true);
        Emp emp3 = new Emp("Luis", "Analyst", 3500, 28, "11223344C", false);

        dpto1.addEmp(emp1);
        dptoRep.guardar(dpto1);
        dpto2.addEmp(emp2);
        dptoRep.guardar(dpto2);
        dpto3.addEmp(emp3);
        dptoRep.guardar(dpto3);


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
