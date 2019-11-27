package com.adaming.groupprojectajms.gestionects.controller;

import com.adaming.groupprojectajms.gestionects.dto.AddCourseDto;
import com.adaming.groupprojectajms.gestionects.dto.CourseDto;
import com.adaming.groupprojectajms.gestionects.entity.Course;
import com.adaming.groupprojectajms.gestionects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestionects.entity.Teacher;
import com.adaming.groupprojectajms.gestionects.exception.CourseAlreadyExistException;
import com.adaming.groupprojectajms.gestionects.exception.NullCourseException;
import com.adaming.groupprojectajms.gestionects.service.CourseService;
import com.adaming.groupprojectajms.gestionects.service.StudentCourseService;
import com.adaming.groupprojectajms.gestionects.service.StudentService;
import com.adaming.groupprojectajms.gestionects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/gestion_ects/api")
public class CourseRestController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping(path = "/courses/teachers/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewCourse(@RequestBody @Valid AddCourseDto courseDto, @PathVariable("id") Long id) {
        try {
            //this.courseService.registerWithInsert(new Course(courseDto.getName(), courseDto.getEcts(), this.teacherService.fetchById(id)));
            this.courseService.register(new Course(courseDto.getName(), courseDto.getEcts(), this.teacherService.fetchById(id)));
        } catch (CourseAlreadyExistException | NullCourseException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(value = "/courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getCourses() {
        Iterable<Course> courses = this.courseService.fetchAll();
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course c : courses) {
            coursesDto.add(c.toDto());
        }
        return coursesDto;
    }

    @GetMapping(value = "/courses/teachers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CourseDto> getCoursesForTeacher(@PathVariable("id") Long id) {
        Teacher teacher = this.teacherService.fetchById(id);
        List<CourseDto> coursesDto = new ArrayList<>();
        for (Course c : teacher.getCourses().stream().distinct().collect(Collectors.toList())) {
            coursesDto.add(c.toDto());
        }
        return coursesDto;
    }

    @DeleteMapping("/courses/{cId}")
    public void deleteCourse(@PathVariable("cId") Long cId) {
        Course course = this.courseService.fetchById(cId);
        for (StudentCourse sc : course.getStudentCourses()) {
            this.studentCourseService.deleteById(sc.getId());
        }
        this.courseService.deleteById(cId);
        this.studentService.checkAcceptations();
    }
}
