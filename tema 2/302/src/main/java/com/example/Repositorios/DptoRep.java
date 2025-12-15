package com.example.Repositorios;

import com.example.Entidades.Dpto;
import com.example.Entidades.Emp;
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
        }
    }

    public void visualizarDepartamento(int id) {
        try {
           Dpto dpto = session.createQuery("FROM Dpto d WHERE id = :id", Dpto.class)
                    .setParameter("id", id)
                    .getSingleResult();
            System.out.println(dpto);

            /*Query<Emp> query = session.createQuery("FROM Emp WHERE dptoElement.id = :dptoId", Emp.class);
            query.setParameter("dptoId", id);
        
            List<Emp> empleados = query.getResultList();*/
            for (Emp emp : dpto.getEmp()) {
                System.out.println(emp);
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
