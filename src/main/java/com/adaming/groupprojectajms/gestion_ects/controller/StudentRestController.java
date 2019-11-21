package com.adaming.groupprojectajms.gestion_ects.controller;


import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.StudentDto;
import com.adaming.groupprojectajms.gestion_ects.dto.TeacherDto;
import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import com.adaming.groupprojectajms.gestion_ects.service.CourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentCourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentService;
import com.adaming.groupprojectajms.gestion_ects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/gestion_ects")
public class StudentRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping(value="/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDto> getTeachers(){
        Iterable<Teacher> teachers=this.teacherService.fetchAll();
        List<TeacherDto> teachersDto=new ArrayList<>();
        for (Teacher t:teachers) {
            teachersDto.add(t.toDto());
        }
        return teachersDto;
    }

    @GetMapping(value="/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudents(){
        Iterable<Student> students=this.studentService.fetchAll();
        List<StudentDto> studentsDto=new ArrayList<>();
        for (Student s:students) {
            studentsDto.add(s.toDto());
        }
        return studentsDto;
    }

    @GetMapping(value="/student/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudent(@PathVariable("id") Long id){
        return this.studentService.fetchById(id).toDto();
    }

    @GetMapping(value="/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getCourses(){
        Iterable<Course> courses=this.courseService.fetchAll();
        List<CourseDto> coursesDto=new ArrayList<>();
        for(Course c:courses){
            coursesDto.add(c.toDto());
        }
        return coursesDto;
    }

    @PostMapping(path="/student/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto addCourse(@PathVariable("id") Long id){
        return getStudent(id);
    }

    @DeleteMapping("/student/{id}/studentcourse/{scId}")
    public StudentDto removeCourse(@PathVariable("id") Long id, @PathVariable("scId") Long scId){
        return getStudent(id);
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