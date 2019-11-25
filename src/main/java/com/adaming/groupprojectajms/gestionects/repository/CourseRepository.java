package com.adaming.groupprojectajms.gestionects.repository;

import com.adaming.groupprojectajms.gestionects.entity.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

    @Query("SELECT c FROM Course As c WHERE c.name=:name")
    Course getByName(String name);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Course WHERE id =:id")
    void deleteById(Long id);
}
