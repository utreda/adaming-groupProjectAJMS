package com.adaming.groupprojectajms.gestionects;

import com.adaming.groupprojectajms.gestionects.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class GestionEctsApplicationTests {

    @Test
    public void testGetSetFirstname() {
      Student student = new Student("Seb","Pivot","seb.pivot@live.fr");
      student.setFirstName("Julia");
        assertEquals("Julia", student.getFirstName());
    }

    @Test
    public void testGetSetLastname() {
        Student student = new Student("Seb","Pivot","seb.pivot@live.fr");
        student.setLastName("Gayral");
        assertEquals("Gayral", student.getLastName());
    }


    @Test
    public void testGetSetEmail() {
        Student student = new Student("Seb","Pivot","seb.pivot@live.fr");
        student.setEmail("ju.gayral@gmail.com");
        assertEquals("ju.gayral@gmail.com", student.getEmail());
    }
    
}
