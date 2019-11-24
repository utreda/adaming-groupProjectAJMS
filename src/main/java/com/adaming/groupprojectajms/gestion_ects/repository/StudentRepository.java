package com.adaming.groupprojectajms.gestion_ects.repository;

import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query("SELECT s FROM Student As s WHERE s.email=:email")
    Student getStudentByEmail(String email);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("DELETE FROM Student WHERE id =:id")
    void deleteById(Long id);

    @Modifying
    @Query("UPDATE Student s SET s.accepted=:accepted WHERE s.id=:id")
    void setAcceptation(@Param("accepted") boolean accepted, @Param("id") Long id);
}
