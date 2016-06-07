package com.soa.model;

import org.hibernate.FetchMode;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "course")
@Entity
@Table(name = "kursy")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int courseId;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	@XmlElement
	@Column(name = "name")
	private String name;

	@XmlAttribute
	@Column(name = "number")
	private int numStudents;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getNumStudents() {
		return numStudents;
	}
	public void setNumStudents(int numStudents) {
		this.numStudents = numStudents;
	}
	
	@Override
	public String toString() {
		return "Course [name=" + name + ", numStudents=" + numStudents + "]";
	}
	
}
