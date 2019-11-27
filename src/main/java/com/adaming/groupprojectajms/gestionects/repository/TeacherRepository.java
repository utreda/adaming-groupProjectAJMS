package com.adaming.groupprojectajms.gestionects.repository;

import com.adaming.groupprojectajms.gestionects.entity.Teacher;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<Teacher,Long> {

    @Query("SELECT t FROM Teacher As t WHERE t.email=:email")
    Teacher getTeacherByEmail(String email);
}
