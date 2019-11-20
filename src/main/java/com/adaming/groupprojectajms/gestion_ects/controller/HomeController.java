package com.adaminggroupProjectAJMS.poc.controller;

import com.adaminggroupProjectAJMS.poc.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private StudentService studentService;

    public StudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/home")
    public ModelAndView choose(){
        return null;
    }

    @GetMapping("/login")
    public ModelAndView loginPage(){
        return null;
    }

    @PostMapping("/students")
    public ModelAndView teacherLogin(){
        return null;
    }

    @PostMapping("/student/{id}")
    public ModelAndView studentLogin(@PathVariable("id") Long id){
        return null;
    }
}
