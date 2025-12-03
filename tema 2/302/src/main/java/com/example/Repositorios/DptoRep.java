package com.example.Repositorios;

import com.example.Entidades.Dpto;

import java.util.List;

import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.HibernateUtil;

public class DptoRep implements Repositorio<Dpto> {
    private static Session session = HibernateUtil.get().openSession();
    

    @Override
    public void guardar(Dpto t) {
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


    public void visualizarDepartamento(int id) {
        try {
            Dpto dpto =(Dpto) session.createQuery("FROM Dpto d where id=:id", Dpto.class);
            
        } catch (Exception e) {
            throw e;
        }
    }

}
