package com.adaminggroupProjectAJMS.poc.service;

import com.adaminggroupProjectAJMS.poc.entity.StudentCourse;
import com.adaminggroupProjectAJMS.poc.repository.StudentCourseRepository;
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
