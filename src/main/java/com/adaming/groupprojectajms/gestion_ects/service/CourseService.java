package com.adaming.groupprojectajms.gestion_ects.service;


import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import com.adaming.groupprojectajms.gestion_ects.exception.CourseAlreadyExistException;
import com.adaming.groupprojectajms.gestion_ects.exception.NullCourseException;
import com.adaming.groupprojectajms.gestion_ects.repository.CourseRepository;
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
        if(c==null){
            throw new NullCourseException();
        }else{
            if(this.courseRepository.getByName(c.getName())==null){
                this.courseRepository.save(c);
            }else{
                throw new CourseAlreadyExistException();
            }
        }
    }

    public Iterable<Course> fetchAll(){
        return this.courseRepository.findAll();
    }

    public Course fetchById(Long id){
        return this.courseRepository.findById(id).orElse(null);
    }
}
