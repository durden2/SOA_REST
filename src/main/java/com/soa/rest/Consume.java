package com.soa.rest;

import java.io.File;

import javax.annotation.security.PermitAll;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.soa.model.Course;
import com.soa.model.Student;

@Path("/consume")
public class Consume {

	@POST
	@Path("/posttext")
	@Consumes("text/plain")
	public Response createFile(String text) {
		String result = "Text created: " + text;
		return Response.status(201).entity(result).build();
	}

	@POST
	@Path("/postjson")
	@Consumes("application/json")
	public Response createStudentJSON(Student student) {
		String result = "Student created: " + student;
		System.out.println("X: " +  student);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		em.persist(student);
		em.getTransaction().commit();
		em.close();
		emf.close();

		return Response.status(201).entity(result).build();
	}
}