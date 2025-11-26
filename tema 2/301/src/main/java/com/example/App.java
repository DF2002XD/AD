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
        Emp emp1 = new Emp("Juan", "Developer", 3000, 30, "12345678A", false);

        dpto1.addEmp(emp1);
        dptoRep.guardar(dpto1);


        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
