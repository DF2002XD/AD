package com.example.Repositorios;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.w3c.dom.events.Event;

import com.example.HibernateUtil;
import com.example.Entidades.Evento;

public class EventoRep {
    private static Session session = HibernateUtil.get().openSession();

    public void mostrarEventos() {
        Query query = session.createQuery("SELECT e FROM Evento e", Evento.class);
        List<Evento> eventos = query.getResultList();
        for (Evento evento : eventos) {
            System.out.println(evento);
        }
    }

	public void modificar(Event evento) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'modificar'");
	}
    
}
