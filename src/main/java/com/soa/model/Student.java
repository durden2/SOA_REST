package com.soa.model;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "student")
@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue
	@Column(name = "id")
	@XmlAttribute
	private Long studentId;

	@XmlElement
	@Column(name = "name")
	private String name;

	@Column(name = "age")
	@XmlAttribute
	private int age;

	@Column(name = "gpa")
	@XmlElement
	private int gpa;

    @OneToMany(orphanRemoval = true)
	@Cascade(value = { CascadeType.SAVE_UPDATE, CascadeType.DELETE })
    @ElementCollection(targetClass = Course.class)
    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
	private List<Course> courses = new ArrayList<Course>();

	public Long getId() {
		return studentId;
	}

	public void setId(Long id){
		this.studentId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getGpa() {
		return gpa;
	}

	public void setGpa(int gpa) {
		this.gpa = gpa;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", gpa=" + gpa + ", courses=" + courses + "]";
	}

}
