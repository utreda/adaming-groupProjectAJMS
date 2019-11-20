package com.adaming.groupprojectajms.gestion_ects.dto;

import com.adaming.groupprojectajms.gestion_ects.entity.Course;

import java.util.List;

public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<CourseDto> coursesDto;
    private List<Boolean> validations;
    private boolean isAccepted;

    public StudentDto(Long id, String firstName, String lastName, String email, List<CourseDto> coursesDto, List<Boolean> validations,boolean isAccepted) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.coursesDto = coursesDto;
        this.validations=validations;
        this.isAccepted = isAccepted;
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

    public List<Boolean> getValidations() {
        return validations;
    }

    public void setValidations(List<Boolean> validations) {
        this.validations = validations;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
