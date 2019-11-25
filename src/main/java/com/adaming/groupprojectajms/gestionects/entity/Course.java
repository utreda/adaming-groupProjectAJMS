package com.adaming.groupprojectajms.gestionects.entity;

import com.adaming.groupprojectajms.gestionects.dto.CourseDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int ects;
    @ManyToOne
    private Teacher teacher;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StudentCourse> studentCourses;

    public Course(){}

    public Course(String name, int ects) {
        this.name = name;
        this.ects = ects;
        this.teacher=teacher;
    }

    public Course(String name, int ects,Teacher teacher) {
        this.name = name;
        this.ects = ects;
        this.teacher=teacher;
    }

    public CourseDto toDto(){
        return new CourseDto(this.id,this.name,this.ects);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
