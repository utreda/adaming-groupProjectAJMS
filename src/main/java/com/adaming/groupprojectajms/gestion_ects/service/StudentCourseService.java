package com.adaming.groupprojectajms.gestion_ects.service;

import com.adaming.groupprojectajms.gestion_ects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestion_ects.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void deleteById(Long id){
        this.studentCourseRepository.deleteById(id);
    }

    @Transactional
    public void delete(StudentCourse sc){
        this.studentCourseRepository.delete(sc);
    }
}
