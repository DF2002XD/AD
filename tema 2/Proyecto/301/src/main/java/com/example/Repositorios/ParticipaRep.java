package com.example.Repositorios;

import java.sql.Date;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.example.HibernateUtil;

public class ParticipaRep {
    private static Session session = HibernateUtil.get().openSession();

    public void registrarParticipacion(int idPersonaje, int idEvento, String fecha, String rol) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.createNativeMutationQuery("INSERT INTO participa (id_personaje, id_evento, fecha, rol) VALUES (?, ?, ?, ?)")
                .setParameter(1, idPersonaje)
                .setParameter(2, idEvento)
                .setParameter(3, Date.valueOf(fecha))
                .setParameter(4, rol)
                .executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }
}