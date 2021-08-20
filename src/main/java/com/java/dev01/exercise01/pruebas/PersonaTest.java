package com.java.dev01.exercise01.pruebas;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import com.java.dev01.exercise01.pojos.Persona;
import com.java.dev01.exercise01.util.HibernateUtil;

public class PersonaTest {
    public static void main(String[] args) {
        // Elimina tabla personas
        HibernateUtil.tableDrop("drop table personas");
        // Crea la tabla personas
        HibernateUtil.sqlExecute("create table personas(pers_id int, pers_paternal VARCHAR(50), pers_maternal VARCHAR(50), pers_name VARCHAR(50))");

        // Crea un SessionFactory y un objeto Session
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        Session session = sessions.openSession();

        // Encapsula el cilo de vida de las operaciones en una transaccion
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Crea un objeto persona y la graba a la base de datos
            Persona p1 = new Persona();
            p1.setPersPaternal("Valencia");
            p1.setPersMaternal("LLamoca");
            p1.setPersName("Danyer");
            session.save(p1); // pase por referencia

            // Crea otra persona y la agrega a la base de datos
            Persona p2 = new Persona();
            p2.setPersPaternal("Valencia");
            p2.setPersMaternal("Castro");
            p2.setPersName("Oscar");
            session.save(p2);

            // Crea tercera persona y la agrega a la base de datos
            Persona p3 = new Persona();
            p3.setPersPaternal("Aldea");
            p3.setPersMaternal("Pezo");
            p3.setPersName("Juan");
            session.save(p3);

            // Crea tercera persona y la agrega a la base de datos
            Persona p4 = new Persona();
            p4.setPersPaternal("Vasquez");
            p4.setPersMaternal("Cardenas");
            p4.setPersName("Axel");
            session.save(p4);

            // Obtiene objetos de la base de datos
            Persona persona = (Persona) session.get(Persona.class, p1.getPersId());
            System.out.println("Primer registro => " + persona.getPersPaternal() +", "+ persona.getPersName());

            persona = (Persona) session.get(Persona.class, p2.getPersId());
            System.out.println("Segunda registro => " + persona.getPersPaternal() +", "+ persona.getPersName());

            persona = (Persona) session.get(Persona.class, p3.getPersId());
            System.out.println("Tercer registro => " + persona.getPersPaternal() +", "+ persona.getPersName());

            persona = (Persona) session.get(Persona.class, p4.getPersId());
            System.out.println("Cuarta registro => " + persona.getPersPaternal() +", "+ persona.getPersName());

            tx.commit();
            tx = null;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Muestra los registros
        HibernateUtil.dataSelect("select * from personas");
    }
}