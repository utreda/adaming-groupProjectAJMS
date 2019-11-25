package com.adaming.groupprojectajms.gestionects.controller;

import com.adaming.groupprojectajms.gestionects.dto.StudentDto;
import com.adaming.groupprojectajms.gestionects.entity.Course;
import com.adaming.groupprojectajms.gestionects.entity.Student;
import com.adaming.groupprojectajms.gestionects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestionects.entity.Teacher;
import com.adaming.groupprojectajms.gestionects.service.StudentService;
import com.adaming.groupprojectajms.gestionects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gestion_ects/api")
public class StudentRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudents() {
        Iterable<Student> students = this.studentService.fetchAll();
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Student s : students) {
            studentsDto.add(s.toDto());
        }
        return studentsDto;
    }

    @GetMapping(value = "/students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public StudentDto getStudent(@PathVariable("id") Long id) {
        return this.studentService.fetchById(id).toDto();
    }

    @GetMapping(value = "/teachers/{id}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudentsForTeacher(@PathVariable("id") Long id) {
        Teacher teacher = this.teacherService.fetchById(id);
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Course c : teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            for (StudentCourse sc : c.getStudentCourses()) {
                studentsDto.add(sc.getStudent().toDto());
            }
        }
        return studentsDto;
    }

    @GetMapping(value = "/teachers/{tId}/courses/{cId}/students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StudentDto> getStudentsForTeacherForCourse(@PathVariable("tId") Long tId, @PathVariable("cId") Long cId) {
        Teacher teacher = this.teacherService.fetchById(tId);
        List<StudentDto> studentsDto = new ArrayList<>();
        for (Course c : teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            if (c.getId() == cId) {
                for (StudentCourse sc : c.getStudentCourses()) {
                    studentsDto.add(sc.getStudent().toDto());
                }
            }
        }
        return studentsDto;
    }

}
