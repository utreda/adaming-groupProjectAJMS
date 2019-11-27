package com.adaming.groupprojectajms.gestionects.service;

import com.adaming.groupprojectajms.gestionects.entity.Student;
import com.adaming.groupprojectajms.gestionects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestionects.exception.NullUserException;
import com.adaming.groupprojectajms.gestionects.exception.UserAlreadyExistException;
import com.adaming.groupprojectajms.gestionects.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public void register(Student s) throws UserAlreadyExistException, NullUserException {
        if (s == null) {
            throw new NullUserException();
        } else {
            if (this.studentRepository.getStudentByEmail(s.getEmail()) == null) {
                this.studentRepository.save(s);
            } else {
                throw new UserAlreadyExistException();
            }
        }
    }

    public void checkAcceptation(Student s){
        int sects=0;
        for(StudentCourse sc:s.getStudentCourses()){
            if(sc.getValidated()){
                sects+=sc.getCourse().getEcts();
            }
        }
        if(sects>=20){
            s.setAccepted(true);
        }else{
            s.setAccepted(false);
        }
    }

    public void checkAcceptations() {
        Iterable<Student> students = this.fetchAll();
        for (Student s : students) {
            int sects = 0;
            for (StudentCourse sc : s.getStudentCourses()) {
                if (sc.getValidated()) {
                    sects += sc.getCourse().getEcts();
                }
            }
            if (sects >= 20) {
                s.setAccepted(true);
                this.studentRepository.save(s);
            } else {
                s.setAccepted(false);
                this.studentRepository.save(s);
            }
        }
    }

    public void updateValidation(Long sId, Long cId){
        Student student=this.studentRepository.findById(sId).orElse(null);
        if (student!=null) {
            for (StudentCourse sc : student.getStudentCourses()) {
                if (sc.getCourse().getId().equals(cId)) {
                    if (sc.getValidated()) {
                        sc.setValidated(false);
                    } else {
                        sc.setValidated(true);
                    }
                }
            }
            this.checkAcceptation(student);
        }
        this.studentRepository.save(student);
    }

    public Iterable<Student> fetchAll() {
        return this.studentRepository.findAll();
    }

    public Student fetchById(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }
}
