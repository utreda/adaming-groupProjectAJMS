package com.adaming.groupprojectajms.gestion_ects.service;

import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import com.adaming.groupprojectajms.gestion_ects.exception.NullUserException;
import com.adaming.groupprojectajms.gestion_ects.exception.UserAlreadyExistException;
import com.adaming.groupprojectajms.gestion_ects.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void register(Teacher t) throws UserAlreadyExistException, NullUserException {
        if(t==null){
            throw new NullUserException();
        }else{
            if(this.teacherRepository.getTeacherByEmail(t.getEmail())==null){
                this.teacherRepository.save(t);
            }else{
                throw new UserAlreadyExistException();
            }
        }
    }

    public Iterable<Teacher> fetchAll(){
        return this.teacherRepository.findAll();
    }

    public Teacher fetchById(Long id){
        return this.teacherRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id){
        this.teacherRepository.deleteById(id);
    }

    @Transactional
    public void delete(Teacher t){
        this.teacherRepository.delete(t);
    }
}