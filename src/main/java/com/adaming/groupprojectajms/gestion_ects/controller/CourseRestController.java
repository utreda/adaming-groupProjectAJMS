package com.adaming.groupprojectajms.gestion_ects.controller;

import com.adaming.groupprojectajms.gestion_ects.dto.AddCourseDto;
import com.adaming.groupprojectajms.gestion_ects.dto.CourseDto;
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
public class CourseRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping(path="/teacher/{id}/courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> addNewCourse(@RequestBody @Valid AddCourseDto courseDto, @PathVariable("id") Long id){
        try {
            this.courseService.register(new Course(courseDto.getName(),courseDto.getEcts(),this.teacherService.fetchById(id)));
        } catch (CourseAlreadyExistException | NullCourseException e) {
            e.printStackTrace();
        }
        return getCoursesForTeacher(id);
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

    @GetMapping(value="/teacher/{id}/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getCoursesForTeacher(@PathVariable("id") Long id){
        Teacher teacher=this.teacherService.fetchById(id);
        List<CourseDto> coursesDto=new ArrayList<>();
        for (Course c:teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            coursesDto.add(c.toDto());
        }
        return coursesDto;
    }

    @DeleteMapping("/course/{cId}")
    public List<CourseDto> deleteCourse(@PathVariable("cId") Long cId){
        Course course=this.courseService.fetchById(cId);
        for(StudentCourse sc:course.getStudentCourses()){
            this.studentCourseService.deleteById(sc.getId());
        }
        this.courseService.deleteById(cId);
        this.studentService.checkAcceptations();
        return getCourses();
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public StudentCourseService getStudentCourseService() {
        return studentCourseService;
    }

    public void setStudentCourseService(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
    }

    public TeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
