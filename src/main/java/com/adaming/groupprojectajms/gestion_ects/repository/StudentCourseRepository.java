package com.adaming.groupprojectajms.gestion_ects.repository;

import com.adaming.groupprojectajms.gestion_ects.entity.StudentCourse;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface StudentCourseRepository extends CrudRepository<StudentCourse, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM StudentCourse WHERE id =:id")
    void deleteById(Long id);
}
