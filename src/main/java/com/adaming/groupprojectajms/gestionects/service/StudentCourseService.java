package com.adaming.groupprojectajms.gestionects.service;

import com.adaming.groupprojectajms.gestionects.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public void deleteById(Long id) {
        this.studentCourseRepository.deleteById(id);
    }
}
