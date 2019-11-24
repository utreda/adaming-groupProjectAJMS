package com.adaming.groupprojectajms.gestion_ects.controller;

import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.StudentDto;
import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.entity.StudentCourse;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gestion_ects")
public class StudentRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseService studentCourseService;

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

    @GetMapping(value="/teacher/{id}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudentsForTeacher(@PathVariable("id") Long id){
        Teacher teacher=this.teacherService.fetchById(id);
        List<StudentDto> studentsDto=new ArrayList<>();
        for (Course c:teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            for (StudentCourse sc:c.getStudentCourses()) {
                studentsDto.add(sc.getStudent().toDto());
            }
        }
        return studentsDto;
    }

    @GetMapping(value="/teacher/{tId}/course/{cId}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudentsForTeacherForCourse(@PathVariable("tId") Long tId,@PathVariable("cId") Long cId){
        Teacher teacher=this.teacherService.fetchById(tId);
        List<CourseDto> coursesDto=new ArrayList<>();
        List<StudentDto> studentsDto=new ArrayList<>();
        for (Course c:teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            if(c.getId()==cId){
                for (StudentCourse sc:c.getStudentCourses()) {
                    studentsDto.add(sc.getStudent().toDto());
                }
            }
        }
        return studentsDto;
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
