package com.adaming.groupprojectajms.gestion_ects.controller;


import com.adaming.groupprojectajms.gestion_ects.service.CourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentCourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showAll(){
        return null;
    }

    @GetMapping(value="/teacher/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView showForTeacher(@PathVariable("id") Long id){
        return null;
    }

    @GetMapping(value="/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView showForStudent(@PathVariable("id") Long id){
        return null;
    }

    @PostMapping(path="/student/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
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