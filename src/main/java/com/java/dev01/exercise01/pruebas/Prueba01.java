package com.java.dev01.exercise01.pruebas;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import com.java.dev01.exercise01.pojos.Persona;
import com.java.dev01.exercise01.util.HibernateUtil;

public class Prueba01 {
    public static void main(String[] args) {
        // Setea las tablas de la base de datos
        HibernateUtil.droptable("drop table empleado");
        HibernateUtil.setup("create table empleado ( id int, cnombre VARCHAR(20))");

        // Crea un SessionFactory y un objeto Session
        SessionFactory sessions = new Configuration().configure().buildSessionFactory();
        Session session = sessions.openSession();

        // Encapsula el cilo de vida de las operaciones en una transaccion
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Crea un objeto persona y la graba a la base de datos
            Persona p1 = new Persona();
            p1.setNombre("Danyer Valencia");
            session.save(p1); // pase por referencia

            // Crea otra persona y la agrega a la base de datos
            Persona p2 = new Persona();
            p2.setNombre("Oscar Valencia");
            session.save(p2);

            // Crea tercera persona y la agrega a la base de datos
            Persona p3 = new Persona();
            p3.setNombre("Juan Aldea");
            session.save(p3);

            // Obtiene objetos de la base de datos
            Persona persona = (Persona) session.get(Persona.class, p1.getId());
            System.out.println("Primera persona = " + persona.getNombre());

            persona = (Persona) session.get(Persona.class, p2.getId());
            System.out.println("Segunda Persona = " + persona.getNombre());

            persona = (Persona) session.get(Persona.class, p3.getId());
            System.out.println("Tercera Persona = " + persona.getNombre());

            tx.commit();
            tx = null;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        // Muestra las tablas
        HibernateUtil.checkData("select * from empleado");
    }
}