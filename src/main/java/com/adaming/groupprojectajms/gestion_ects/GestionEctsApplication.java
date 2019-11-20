package com.adaming.groupprojectajms.gestion_ects;

import com.adaming.groupprojectajms.gestion_ects.entity.Course;
import com.adaming.groupprojectajms.gestion_ects.entity.Student;
import com.adaming.groupprojectajms.gestion_ects.entity.StudentCourse;
import com.adaming.groupprojectajms.gestion_ects.entity.Teacher;
import com.adaming.groupprojectajms.gestion_ects.service.CourseService;
import com.adaming.groupprojectajms.gestion_ects.service.StudentService;
import com.adaming.groupprojectajms.gestion_ects.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class GestionEctsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GestionEctsApplication.class, args);
    }

    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        //On persiste des professeurs en base de données
        Teacher t1=new Teacher("X","X","x.x@gmail.com");
        Teacher t2=new Teacher("Y","Y","y.y@gmail.com");
        Teacher t3=new Teacher("Z","Z","z.z@gmail.com");
        List<Teacher> ts=new ArrayList<>(Arrays.asList(t1,t2,t3));
        for (Teacher t:ts) {
            this.teacherService.register(t);
        }

        //On persiste des matières avec leur professeur affiliés
        Course c1=new Course("Math",10,t2);
        Course c2=new Course("English",6,t2);
        Course c3=new Course("French",4,t2);
        Course c4=new Course("History",5,t3);
        Course c5=new Course("Physics",8,t1);
        Course c6=new Course("Biology",7,t1);
        List<Course> cs=new ArrayList<>(Arrays.asList(c1,c2,c3,c4,c5,c6));
        for (Course c:cs) {
            this.courseService.register(c);
        }

        //On crée des étudiants
        Student s1=new Student("A","A","a.a@gmail.com");
        Student s2=new Student("B","B","b.b@gmail.com");
        Student s3=new Student("C","C","c.c@gmail.com");
        Student s4=new Student("D","D","d.d@gmail.com");
        //On crée des relations étudiant-matière pour chaque étudiant puis on les lui affilie
        //Etudiant A
        StudentCourse sc1=new StudentCourse(s1,c2,true);
        StudentCourse sc2=new StudentCourse(s1,c3,true);
        StudentCourse sc3=new StudentCourse(s1,c4,false);
        s1.setStudentCourses(new ArrayList<>(Arrays.asList(sc1,sc2,sc3)));
        //On additionne les ects selon que la matière est validée ou pas
        int sects1=0;
        for(StudentCourse sc:s1.getStudentCourses()){
            if(sc.getValidated()){
                sects1+=sc.getCourse().getEcts();
            }
        }
        //On valide ou pas le passage de l'élève pour le semestre
        if(sects1>=20){
            s1.setAccepted(true);
        }
        //Etudiant B
        StudentCourse sc4=new StudentCourse(s2,c1,true);
        StudentCourse sc5=new StudentCourse(s2,c5,true);
        StudentCourse sc6=new StudentCourse(s2,c6,true);
        StudentCourse sc7=new StudentCourse(s2,c2,false);
        s2.setStudentCourses(new ArrayList<>(Arrays.asList(sc4,sc5,sc6,sc7)));
        int sects2=0;
        for(StudentCourse sc:s2.getStudentCourses()){
            if(sc.getValidated()){
                sects2+=sc.getCourse().getEcts();
            }
        }
        if(sects2>=20){
            s2.setAccepted(true);
        }
        //Etudiant C
        StudentCourse sc8=new StudentCourse(s3,c3,true);
        StudentCourse sc9=new StudentCourse(s3,c4,false);
        s3.setStudentCourses(new ArrayList<>(Arrays.asList(sc8,sc9)));
        int sects3=0;
        for(StudentCourse sc:s3.getStudentCourses()){
            if(sc.getValidated()){
                sects3+=sc.getCourse().getEcts();
            }
        }
        if(sects3>=20){
            s3.setAccepted(true);
        }
        //Etudiant D
        StudentCourse sc10=new StudentCourse(s4,c2,true);
        s4.setStudentCourses(new ArrayList<>(Arrays.asList(sc10)));
        int sects4=0;
        for(StudentCourse sc:s4.getStudentCourses()){
            if(sc.getValidated()){
                sects4+=sc.getCourse().getEcts();
            }
        }
        if(sects4>=20){
            s4.setAccepted(true);
        }

        //On persiste les étudiants en base de données
        List<Student> ss=new ArrayList<>(Arrays.asList(s1,s2,s3,s4));
        for (Student s:ss) {
            this.studentService.register(s);
        }
    }
}
