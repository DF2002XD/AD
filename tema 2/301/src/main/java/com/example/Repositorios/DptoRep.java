package com.example.Repositorios;

import com.example.Entidades.Dpto;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.HibernateUtil;

public class DptoRep implements Repositorio<Dpto> {
    private static Session session = HibernateUtil.get().openSession();
    Transaction tx = null;

    @Override
    public void guardar(Dpto t) {
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
