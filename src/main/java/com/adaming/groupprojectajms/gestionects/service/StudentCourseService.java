package com.adaming.groupprojectajms.gestionects.service;

import com.adaming.groupprojectajms.gestionects.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public void deleteById(Long id) {
        this.studentCourseRepository.deleteById(id);
    }
}
