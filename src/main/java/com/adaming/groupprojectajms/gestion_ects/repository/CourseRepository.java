package com.adaming.groupprojectajms.gestion_ects.repository;

import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

    @Query("SELECT c FROM Course As c WHERE c.name=:name")
    Course getByName(String name);
}
