package com.adaming.groupprojectajms.gestion_ects.controller;


import com.adaming.groupprojectajms.gestion_ects.dto.StudentDto;
import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.service.CourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentCourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/webapi")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping(value="/teacher/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getAll(){
        Iterable<Student> students=this.studentService.fetchAll();
        List<StudentDto> studentsDto=new ArrayList<>();
        for (Student s:students) {
            studentsDto.add(s.toDto());
        }
        return studentsDto;
    }

    @GetMapping(value="/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto get(@PathVariable("id") Long id){
        return this.studentService.fetchById(id).toDto();
    }

    @PostMapping(path="/student/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto addCourse(@PathVariable("id") Long id){
        return get(id);
    }

    @DeleteMapping("/student/{id}/studentcourse/{scId}")
    public StudentDto removeCourse(@PathVariable("id") Long id, @PathVariable("scId") Long scId){
        return get(id);
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public StudentCourseService getStudentCourseService() {
        return studentCourseService;
    }

    public void setStudentCourseService(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }
}