package com.example;

import org.hibernate.Session;

import com.example.Entidades.Dpto;
import com.example.Entidades.Emp;
import com.example.Repositorios.DptoRep;
import com.example.Repositorios.EmpRep;

public class App {
    public static void main(String[] args) {
        System.out.println("Test");

        Session session = HibernateUtil.get().openSession();

        DptoRep dptoRep = new DptoRep();
        EmpRep empRep = new EmpRep();

        dptoRep.visualizarDepartamento(2);

        Emp empleadoEmp = new Emp("Antonio", "Investigador", 3500, 45,"12345678A", false);

        //empRep.anhadirEmpleado(1, empleadoEmp);
        //empRep.actualizarJefeDepartamento(2, 4);

        //dptoRep.eliminarDepartamento(4);

        session.close();
        System.out.println("Finalizando la conexion a MySQL");
    }
}
