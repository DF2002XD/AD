package com.example.Repositorios;

import java.util.List;
import org.hibernate.query.Query;
import com.example.HibernateUtil;
import com.example.Entidades.Traje;

import org.hibernate.Session;

public class TrajeRep {
    private static Session session = HibernateUtil.get().openSession();

    public void mostrarTrajes() {
        Query query = session.createQuery("SELECT t FROM Traje t", Traje.class);
        List<Traje> trajes = query.getResultList();
        for (Traje traje : trajes) {
            System.out.println(traje);
        }
    }


}
