package com.soa.rest;

import java.io.File;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import javax.persistence.*;
import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.soa.model.Course;
import com.soa.model.Student;

@Path("/produce")
public class Produce {
	Student student = new Student();

	private static final String TEXT_FILE_PATH = "D:\\SOA\\pliki\\plik.txt";
	private static final String IMAGE_FILE_PATH = "D:\\SOA\\pliki\\obrazek.png";
	private static final String PDF_FILE_PATH = "D:\\SOA\\pliki\\pdf.pdf";

	@GET
	@RolesAllowed("Manager")
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {
		String result = "Restful example : " + msg;
		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/gettext")
	@Produces("text/plain")
	public Response getFile() {
		File file = new File(TEXT_FILE_PATH);

		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"plikos.txt\"");
		return response.build();
	}

	@GET
	@Path("/getimage")
	@Produces("image/png")
	public Response getImage() {
		File file = new File(IMAGE_FILE_PATH);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"obrazek.png\"");
		return response.build();
	}

	@GET
	@Path("/getpdf")
	@Produces("application/pdf")
	public Response getPDF() {
		File file = new File(PDF_FILE_PATH);
		ResponseBuilder response = Response.ok((Object) file);
		response.header("Content-Disposition", "attachment; filename=\"pdf.pdf\"");
		return response.build();
	}

	@GET
	@Path("/getjson")
	@PermitAll
	@Produces("application/json")
	public Student getStudentJSON() {

		Course course1 = new Course();
		course1.setName("Course 1");
		course1.setNumStudents(22);

		Course course2 = new Course();
		course2.setName("Course 2");
		course2.setNumStudents(40);

		student.setName("gandi");
		student.setAge(22);
		student.setGpa(5);
		student.getCourses().add(course1);
		student.getCourses().add(course2);

		return student;
	}


	@GET
	@Path("/criteria")
	@Produces("application/xml")
	public List<Student> getStud(@QueryParam("name") String name, @QueryParam("age") int age, @QueryParam("gpa") int gpa){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entitymanager = emfactory.createEntityManager();

		CriteriaBuilder builder;
		builder = entitymanager.getCriteriaBuilder();
		CriteriaQuery<Student> criteriaQuery = builder.createQuery(Student.class);
		Root<Student> personRoot = criteriaQuery.from(Student.class);

		List<Predicate> predicates = new ArrayList<Predicate>();
		if(name!= null)
			predicates.add(builder.like(personRoot.<String>get("name"),name+"%"));
			predicates.add(builder.equal(personRoot.<String>get("age"),age));
			predicates.add(builder.equal(personRoot.<String>get("gpa"),gpa));

		criteriaQuery.select(personRoot).where(predicates.toArray(new Predicate[]{}));
		return entitymanager.createQuery(criteriaQuery).getResultList();
		/*EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entitymanager = emfactory.createEntityManager();

		TypedQuery<Student> query =
				entitymanager.createQuery("SELECT c FROM Student c where c.age >"+age, Student.class);
		List<Student> results = query.getResultList();*/

	}

	@GET
	@Path("/getxml")
	@Produces("application/xml")
	public Student getStudentXML() {

		Course course1 = new Course();
		course1.setName("Course 1");
		course1.setNumStudents(22);

		Course course2 = new Course();
		course2.setName("Course 2");
		course2.setNumStudents(40);

		student.setName("gandi");
		student.setAge(22);
		student.setGpa(5);
		student.getCourses().add(course1);
		student.getCourses().add(course2);

		return student;
	}

	@GET
	@Path("/getxmlSpec/{parm}")
	public Response getStudentXMLSpec(@QueryParam("parm") int parm) {
		Long p = 1L;
		System.out.print("Id: " + p);
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entitymanager = emfactory.createEntityManager();
		Student employee = entitymanager.getReference(Student.class, p);

		String result = "Student name : " + employee.getName();
		return Response.status(200).entity(result).build();
	}

	@POST
	@RolesAllowed("Manager")
	@Path("/post")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public Response createMessage(@FormParam("name") String name, @FormParam("year") int year, @FormParam("gpa") int gpa){
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

		Student student = new Student();
		student.setName(name);
		student.setAge(year);
		student.setGpa(gpa);

		entityManager.getTransaction().begin();
		entityManager.persist(student);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		String result = "Got: " + name + " year: " + year + " Gpa" + gpa;
		return Response.status(201).entity(result).build();
	}

	@PUT
	@RolesAllowed("Manager")
	@Path("/student")
	public Response addStudent(){
		student.setName("gandi");
		student.setAge(22);
		student.setGpa(5);

		String result = "Restful example : " + student.getName() + " " + student.getAge() + " " + student.getGpa();
		return Response.status(200).entity(result).build();

	}

	@DELETE
	@Path("st")
	public Response deleteStudent(){

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("myDatabase");
		EntityManager em = emfactory.createEntityManager();

		Long p = 1L;
		Student employee = em.find(Student.class, p);

		em.getTransaction().begin();
		em.remove(employee);
		em.getTransaction().commit();

		String result = "Deleted!";
		return Response.status(200).entity(result).build();

	}
}
