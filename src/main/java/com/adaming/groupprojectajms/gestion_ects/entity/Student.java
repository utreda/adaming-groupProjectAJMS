package com.adaming.groupprojectajms.gestion_ects.entity;

import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.StudentDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
    private boolean accepted;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<StudentCourse> studentCourses;

    public Student(){}

    public Student(String firstName, String lastName, @Email @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public StudentDto toDto(){
        List<CourseDto> coursesDto=new ArrayList<>();
        for(StudentCourse sc:this.studentCourses){
            coursesDto.add(sc.getCourse().toDto());
        }
        List<Boolean> validations=new ArrayList<>();
        for(StudentCourse sc:this.studentCourses){
            validations.add(sc.getValidated());
        }
        return new StudentDto(this.id,this.firstName,this.lastName,this.email,coursesDto,validations,this.accepted);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public List<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(List<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }
}
