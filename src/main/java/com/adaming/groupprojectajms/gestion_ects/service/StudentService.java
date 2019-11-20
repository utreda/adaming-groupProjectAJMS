package com.adaming.groupprojectajms.gestion_ects.service;

import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.exception.NullUserException;
import com.adaming.groupprojectajms.gestion_ects.exception.UserAlreadyExistException;
import com.adaming.groupprojectajms.gestion_ects.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void register(Student s) throws UserAlreadyExistException, NullUserException {
        if(s==null){
            throw new NullUserException();
        }else{
            if(this.studentRepository.getStudentByEmail(s.getEmail())==null){
                this.studentRepository.save(s);
            }else{
                throw new UserAlreadyExistException();
            }
        }
    }

    public Iterable<Student> fetchAll(){
        return this.studentRepository.findAll();
    }

    public Student fetchById(Long id){
        return this.studentRepository.findById(id).orElse(null);
    }
}
