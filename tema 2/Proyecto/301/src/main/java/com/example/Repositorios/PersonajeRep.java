package com.example.Repositorios;

import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.HibernateUtil;
import com.example.Entidades.Personaje;

public class PersonajeRep implements Repositorio<Personaje> {
    private static Session session = HibernateUtil.get().openSession();

    @Override
    public void guardar(Personaje t) {
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
    public void modificar(Personaje t) {
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

    public void borrarPorId(int id) {
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Personaje personaje = session.get(Personaje.class, id);
            if (personaje != null) {
                session.remove(personaje);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }
    }

    public void mostrarPersonajes() {
        Query query = session.createQuery("SELECT p FROM Personaje p", Personaje.class);
        List<Personaje> personajes = query.getResultList();
        for (Personaje personaje : personajes) {
            System.out.println(personaje);
        }
    }

    public void mostrarPersonajeCompleto(int id) {
        Personaje personaje = session.get(Personaje.class, id);
        if (personaje != null) {
            System.out.println(personaje);
            System.out.println("Habilidades:");
            personaje.getListahabilidad().forEach(habilidad -> System.out.println(habilidad));
            System.out.println("Eventos:");
            personaje.getListaparticipa().forEach(evento -> System.out.println(evento));
        } else {
            System.out.println("Personaje no encontrado.");
        }
    }

    public void mostrarPersonajesEnEvento(int eventoId) {
        Query<Personaje> query = session.createQuery(
                "SELECT p.personaje FROM Participa p WHERE p.evento.id = :eventoId", Personaje.class);
        query.setParameter("eventoId", eventoId);
        List<Personaje> personajes = query.getResultList();
        for (Personaje personaje : personajes) {
            System.out.println(personaje);
        }
    }

}