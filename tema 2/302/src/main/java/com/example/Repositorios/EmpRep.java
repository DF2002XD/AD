package com.example.Repositorios;

import com.example.Entidades.Dpto;
import com.example.Entidades.Emp;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.HibernateUtil;

public class EmpRep implements Repositorio<Emp> {
    private static Session session = HibernateUtil.get().openSession();
    

    @Override
    public void guardar(Emp t) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }


    public void anhadirEmpleado(int i, Emp empleadoEmp) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Dpto dpto = session.createQuery("FROM Dpto WHERE id = :id", Dpto.class)
                    .setParameter("id", i)
                    .getSingleResult();
            dpto.addEmp(empleadoEmp);
            session.persist(dpto);
            session.persist(empleadoEmp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }


    public void actualizarJefeDepartamento(int i, int j) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Emp emp = session.createQuery("FROM Emp e WHERE e.dptoElement.id = :id1 AND e.id = :id2", Emp.class)
                    .setParameter("id1", i)
                    .setParameter("id2", j)
                    .getSingleResult();

            
            emp.setEsJefe(true);
            session.persist(emp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

}
