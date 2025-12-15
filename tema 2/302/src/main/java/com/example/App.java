package com.example;

import org.hibernate.Session;

import com.example.Entidades.Dpto;
import com.example.Entidades.Emp;
import com.example.Repositorios.DptoRep;
import com.example.Repositorios.EmpRep;

import org.hibernate.Transaction;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        DptoRep dptoRep = new DptoRep();
        EmpRep empRep = new EmpRep();

        Dpto dpto1 = new Dpto("Finanzas", "Madrid");
        Dpto dpto2 = new Dpto("Recursos Humanos", "Barcelona");
        Dpto dpto3 = new Dpto("IT", "Valencia");


        Emp emp1 = new Emp("Juan", "Developer", 3000, 30, "12345678A", false);
        Emp emp2 = new Emp("Maria", "Manager", 5000, 40, "87654321B", true);
        Emp emp3 = new Emp("Luis", "Analyst", 3500, 28, "11223344C", false);

         Transaction tx = null;
        try {
             tx = session.beginTransaction();
             session.persist(dpto1);
        session.persist(dpto2);
        session.persist(dpto3);
        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        dpto1.addEmp(emp1);
        dpto2.addEmp(emp2);
        dpto3.addEmp(emp3);
        session.persist(dpto1);
        session.persist(dpto2);
        session.persist(dpto3);
        session.persist(emp1);
        session.persist(emp2);
        session.persist(emp3);
        tx.commit();
        } catch (Exception e) {
           
        }
       
        


       /* empRep.guardar(emp1);
        empRep.guardar(emp2);
        empRep.guardar(emp3);
        dptoRep.guardar(dpto1);
        dptoRep.guardar(dpto2);
        dptoRep.guardar(dpto3);
        
        dpto1.addEmp(emp1);
        dpto2.addEmp(emp2);
        dpto3.addEmp(emp3);

        dptoRep.guardar(dpto1);
        dptoRep.guardar(dpto2);
        dptoRep.guardar(dpto3);

        empRep.guardar(emp1);
        empRep.guardar(emp2);
        empRep.guardar(emp3);*/
        
        


        dptoRep.visualizarDepartamento(2);

        Emp empleadoEmp = new Emp("Antonio", "Investigador", 3500, 45,"12345678A", false);

        empRep.anhadirEmpleado(1, empleadoEmp);
        empRep.actualizarJefeDepartamento(2, 4);

        //dptoRep.eliminarDepartamento(4);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
