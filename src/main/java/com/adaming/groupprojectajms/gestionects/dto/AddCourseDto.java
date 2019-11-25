package com.adaming.groupprojectajms.gestionects.dto;

import java.util.List;

public class AddCourseDto {

    private String name;
    private int ects;

    public AddCourseDto() {
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
}
