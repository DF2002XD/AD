package com.example.Repositorios;

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
        } finally {
            session.close();
        }
    }

}
