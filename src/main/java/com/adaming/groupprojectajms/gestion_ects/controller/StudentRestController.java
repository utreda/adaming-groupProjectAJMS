package com.adaming.groupprojectajms.gestion_ects.controller;


import com.adaming.groupprojectajms.gestion_ects.dto.AddCourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.StudentDto;
import com.adaming.groupprojectajms.gestion_ects.dto.TeacherDto;
import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import com.adaming.groupprojectajms.gestion_ects.exception.CourseAlreadyExistException;
import com.adaming.groupprojectajms.gestion_ects.exception.NullCourseException;
import com.adaming.groupprojectajms.gestion_ects.service.CourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentCourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentService;
import com.adaming.groupprojectajms.gestion_ects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping(value="/teacher/{id}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getCoursesForTeacher(@PathVariable("id") Long id){
        Teacher teacher=this.teacherService.fetchById(id);
        List<CourseDto> coursesDto=new ArrayList<>();
        for (Course c:teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            coursesDto.add(c.toDto());
        }
        return coursesDto;
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

    @PostMapping(path="/teacher/{id}/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> addNewCourse(@RequestBody @Valid AddCourseDto courseDto, @PathVariable("id") Long id){
        try {
            this.courseService.register(new Course(courseDto.getName(),courseDto.getEcts(),this.teacherService.fetchById(id)));
        } catch (CourseAlreadyExistException | NullCourseException e) {
            e.printStackTrace();
        }
        return getCoursesForTeacher(id);
    }

    @DeleteMapping("/course/{cId}")
    public List<CourseDto> deleteCourse(@PathVariable("cId") Long cId){
        Course course=this.courseService.fetchById(cId);
        for(StudentCourse sc:course.getStudentCourses()){
            this.studentCourseService.deleteById(sc.getId());
        }
        this.courseService.deleteById(cId);
        return getCourses();
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