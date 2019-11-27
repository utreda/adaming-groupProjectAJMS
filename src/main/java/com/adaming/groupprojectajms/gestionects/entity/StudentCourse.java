package com.adaming.groupprojectajms.gestionects.entity;

import javax.persistence.*;

@Entity
public class StudentCourse{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn
    private Student student;
    @ManyToOne
    @JoinColumn
    private Course course;
    private Boolean validated;

    public StudentCourse() {
    }

    public StudentCourse(Student student, Course course, Boolean validated) {
        this.student = student;
        this.course = course;
        this.validated = validated;
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

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
