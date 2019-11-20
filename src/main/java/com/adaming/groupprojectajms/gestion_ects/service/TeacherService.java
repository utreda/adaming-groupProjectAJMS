package com.adaminggroupProjectAJMS.poc.service;

import com.adaminggroupProjectAJMS.poc.entity.Teacher;
import com.adaminggroupProjectAJMS.poc.exception.NullUserException;
import com.adaminggroupProjectAJMS.poc.exception.UserAlreadyExistException;
import com.adaminggroupProjectAJMS.poc.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    public void setTeacherRepository(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public void register(Teacher t) throws UserAlreadyExistException, NullUserException {

    }

    public Iterable<Teacher> fetchAll(){
        return this.teacherRepository.findAll();
    }

    public Teacher fetchById(Long id){
        return this.teacherRepository.findById(id).orElse(null);
    }
}