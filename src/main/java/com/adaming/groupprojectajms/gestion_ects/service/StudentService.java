package com.adaminggroupProjectAJMS.poc.service;

import com.adaminggroupProjectAJMS.poc.entity.Student;
import com.adaminggroupProjectAJMS.poc.exception.NullUserException;
import com.adaminggroupProjectAJMS.poc.exception.UserAlreadyExistException;
import com.adaminggroupProjectAJMS.poc.repository.StudentRepository;
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

    }

    public Iterable<Student> fetchAll(){
        return this.studentRepository.findAll();
    }

    public Student fetchById(Long id){
        return this.studentRepository.findById(id).orElse(null);
    }
}
