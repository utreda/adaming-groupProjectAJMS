package com.adaming.groupprojectajms.gestion_ects.entity;

import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.TeacherDto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="teachers")
public class Teacher{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    @NotNull
    private String email;
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Course> courses;

    public Teacher(){}

    public Teacher(String firstName, String lastName, @Email @NotNull String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public TeacherDto toDto(){
        List<CourseDto> coursesDto=new ArrayList<>();
        for(Course c:this.courses){
            coursesDto.add(c.toDto());
        }
        return new TeacherDto(this.id,this.firstName,this.lastName,this.email,coursesDto);
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
