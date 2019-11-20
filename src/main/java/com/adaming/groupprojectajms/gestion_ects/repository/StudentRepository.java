package com.adaming.groupprojectajms.gestion_ects.repository;

import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query("SELECT s FROM Student As s WHERE s.email=:email")
    Student getStudentByEmail(String email);
}
