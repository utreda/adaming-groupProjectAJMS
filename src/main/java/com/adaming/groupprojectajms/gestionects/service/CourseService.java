package com.adaming.groupprojectajms.gestionects.service;


import com.adaming.groupprojectajms.gestionects.entity.Course;
import com.adaming.groupprojectajms.gestionects.exception.CourseAlreadyExistException;
import com.adaming.groupprojectajms.gestionects.exception.NullCourseException;
import com.adaming.groupprojectajms.gestionects.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Transactional
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

    public void deleteById(Long id){
        this.courseRepository.deleteById(id);
    }
}
