package com.adaminggroupProjectAJMS.poc.controller;

import com.adaminggroupProjectAJMS.poc.service.CourseService;
import com.adaminggroupProjectAJMS.poc.service.StudentCourseService;
import com.adaminggroupProjectAJMS.poc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.awt.*;

@RestController
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/teacher/students")
    public ModelAndView showAll(){
        return null;
    }

    @GetMapping("/teacher/student/{id}")
    public ModelAndView showForTeacher(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping("/student/{id}")
    public ModelAndView showForStudent(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping("/student/{id}")
    public ModelAndView addCourse(@PathVariable("id") Long id){
        return null;
    }

    @DeleteMapping("/student/course/{scId}")
    public ModelAndView removeCourse(@PathVariable("scId") Long scId){
        return null;
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