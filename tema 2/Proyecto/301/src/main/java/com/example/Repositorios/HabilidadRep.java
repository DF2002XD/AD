package com.example.Repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.example.HibernateUtil;
import com.example.Entidades.Habilidad;

public class HabilidadRep implements Repositorio<Habilidad> {
    
    private static Session session = HibernateUtil.get().openSession();

    @Override
    public void guardar(Habilidad t) {
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
    public void modificar(Habilidad t) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(t);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void mostrarHabilidad() {
        Query query = session.createQuery("SELECT h FROM Habilidad h", Habilidad.class);
        List<Habilidad> habilidades = query.getResultList();
        for (Habilidad habilidad : habilidades) {
            System.out.println(habilidad);
        }
    }

    public void borrarPorNombre(String nombre) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Habilidad> query = session.createQuery("FROM Habilidad h WHERE h.nombre = :nombre", Habilidad.class);
            query.setParameter("nombre", nombre);
            Habilidad habilidad = query.uniqueResult();
            if (habilidad != null) {
                session.remove(habilidad);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void mostrarCantidadPersonajesConHabilidad(String nombre) {
        int cantidad = 0;
        Query<Habilidad> query = session.createQuery("FROM Habilidad h WHERE h.nombre = :nombre", Habilidad.class);
        query.setParameter("nombre", nombre);
        Habilidad habilidad = query.uniqueResult();
        if (habilidad != null) {
            cantidad = habilidad.getListapersonaje().size();
        }
        System.out.println("Cantidad de personajes con la habilidad " + nombre + ": " + cantidad);
    }

}
