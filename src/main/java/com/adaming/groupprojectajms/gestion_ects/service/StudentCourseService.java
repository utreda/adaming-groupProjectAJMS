package com.adaming.groupprojectajms.gestion_ects.service;

import com.adaming.groupprojectajms.gestion_ects.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService{

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public StudentCourseRepository getStudentCourseRepository() {
        return studentCourseRepository;
    }

    public void setStudentCourseRepository(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }
}
