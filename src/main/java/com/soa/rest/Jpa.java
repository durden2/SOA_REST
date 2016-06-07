package com.soa.rest;

import com.soa.model.Course;
import com.soa.model.Student;

import javax.persistence.*;
import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Gandi on 06.06.2016.
 */
public class Jpa {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Student student = new Student();
        student.setName("Michal");
        student.setAge(2323);
        student.setGpa(45);

        entityManager.getTransaction().begin();
        entityManager.persist(student);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();

        Long p = Long.valueOf(2);
        EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myDatabase");
        EntityManager entitymanager = emfactory.createEntityManager();
        Student employee = entitymanager.getReference(Student.class, p);

        System.out.print(employee);
    }
}
