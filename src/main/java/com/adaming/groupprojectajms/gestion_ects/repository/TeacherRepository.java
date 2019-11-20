package com.adaming.groupprojectajms.gestion_ects.repository;

import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {

    @Query("SELECT t FROM Teacher As t WHERE t.email=:email")
    Teacher getTeacherByEmail(String email);
}
