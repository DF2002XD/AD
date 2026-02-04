package com.example.Repositorios;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import com.example.HibernateUtil;
import com.example.Entidades.Instituto;

public class InstitutoRep implements Repositorio<Instituto> {
    private static final Session session = HibernateUtil.get().openSession();

    @Override
    public void guardar(Instituto t) {
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

    @Override
    public void eliminar(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Instituto instituto = session.get(Instituto.class, id);
            if (instituto != null) {
                session.remove(instituto);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void mostrar() {
       Query<Instituto> query = session.createQuery("FROM Instituto", Instituto.class);
       List<Instituto> institutos = query.getResultList();
       for (Instituto instituto : institutos) {
        System.out.println(instituto);
       }
    }
}
