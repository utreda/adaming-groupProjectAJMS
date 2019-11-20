package com.adaminggroupProjectAJMS.poc.service;

import com.adaminggroupProjectAJMS.poc.entity.Course;
import com.adaminggroupProjectAJMS.poc.exception.CourseAlreadyExistException;
import com.adaminggroupProjectAJMS.poc.exception.NullCourseException;
import com.adaminggroupProjectAJMS.poc.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void register(Course c) throws CourseAlreadyExistException, NullCourseException {

    }

    public Iterable<Course> fetchAll(){
        return this.courseRepository.findAll();
    }

    public Course fetchById(Long id){
        return this.courseRepository.findById(id).orElse(null);
    }
}
