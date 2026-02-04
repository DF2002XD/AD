package com.example.Repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.HibernateUtil;
import com.example.Entidades.Ciclo;


public class CicloRep implements Repositorio<Ciclo> {
    private static final Session session = HibernateUtil.get().openSession();

    @Override
    public void guardar(Ciclo t) {
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

    @Override
    public void eliminar(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Ciclo ciclo = session.get(Ciclo.class, id);
            if (ciclo != null) {
                session.remove(ciclo);
            }
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

    public void mostrar() {
        Query<Ciclo> query = session.createQuery("SELECT p FROM Ciclo p", Ciclo.class);
        List<Ciclo> ciclos = query.getResultList();
        for (Ciclo c : ciclos) {
            System.out.println(c);
        }
    }
}
