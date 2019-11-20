package com.adaming.groupprojectajms.gestion_ects.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class StudentCourse implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Student student;
    @ManyToOne
    @JoinColumn
    private Course course;
    private boolean isValidated;

    public StudentCourse() {
    }

    public StudentCourse(Student student, Course course, boolean isValidated) {
        this.student = student;
        this.course = course;
        this.isValidated = isValidated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public boolean isValidated() {
        return isValidated;
    }

    public void setValidated(boolean validated) {
        isValidated = validated;
    }
}