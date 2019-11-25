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

    @Transactional
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
                this.studentRepository.setAcceptation(true, s.getId());
            } else {
                this.studentRepository.setAcceptation(false, s.getId());
            }
        }
    }

    public Iterable<Student> fetchAll() {
        return this.studentRepository.findAll();
    }

    public Student fetchById(Long id) {
        return this.studentRepository.findById(id).orElse(null);
    }

    @Transactional
    public void deleteById(Long id) {
        this.studentRepository.deleteById(id);
    }
}
