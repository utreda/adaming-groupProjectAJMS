package com.adaming.groupprojectajms.gestionects.controller;

import com.adaming.groupprojectajms.gestionects.dto.TeacherDto;
import com.adaming.groupprojectajms.gestionects.entity.Teacher;
import com.adaming.groupprojectajms.gestionects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/gestion_ects/api")
public class TeacherRestController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping(value = "/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeacherDto> getTeachers() {
        Iterable<Teacher> teachers = this.teacherService.fetchAll();
        List<TeacherDto> teachersDto = new ArrayList<>();
        for (Teacher t : teachers) {
            teachersDto.add(t.toDto());
        }
        return teachersDto;
    }

    @GetMapping(value = "/teachers/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TeacherDto getTeacher(@PathVariable("id") Long id) {
        Teacher teacher = this.teacherService.fetchById(id);
        teacher.setCourses(teacher.getCourses().stream().distinct().collect(Collectors.toList()));
        return teacher.toDto();
    }
}
