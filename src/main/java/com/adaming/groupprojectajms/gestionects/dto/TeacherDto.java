package com.adaming.groupprojectajms.gestionects.dto;

import java.util.List;

public class TeacherDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDto> coursesDto;

    public TeacherDto(Long id, String firstName, String lastName, String email, List<CourseDto> coursesDto) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.coursesDto = coursesDto;
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

    public List<CourseDto> getCoursesDto() {
        return coursesDto;
    }

    public void setCoursesDto(List<CourseDto> coursesDto) {
        this.coursesDto = coursesDto;
    }
}
