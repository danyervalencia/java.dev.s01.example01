package com.java.dev.s01.example01.pruebas;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.java.dev.s01.example01.entities.Persona;
import com.java.dev.s01.example01.util.HibernateUtil;

public class PersonaTest {
    public static void main(String[] args) {
        Persona p1 = new Persona();
        p1.setPersPaternal("Valencia");
        p1.setPersMaternal("LLamoca");
        p1.setPersName("Danyer");

        Persona p2 = new Persona();
        p2.setPersPaternal("Valencia");
        p2.setPersMaternal("Castro");
        p2.setPersName("Oscar");

        Persona p3 = new Persona();
        p3.setPersPaternal("Aldea");
        p3.setPersMaternal("Pezo");
        p3.setPersName("Juan");

        Persona p4 = new Persona();
        p4.setPersPaternal("Vasquez");
        p4.setPersMaternal("Cardenas");
        p4.setPersName("Axel");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println(transaction);
            session.save(p1);
            session.save(p2);
            session.save(p3);
            session.save(p4);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSession()) {
            //List<Persona> pers = session.createQuery("from Persona", Persona.class).list();
            List<Persona> listP = session.createQuery("FROM Persona WHERE persId > 20").getResultList();
            listP.forEach(s -> System.out.println(s.getPersId() +" - "+ s.getPersName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}