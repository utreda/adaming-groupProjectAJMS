package com.adaming.groupprojectajms.gestion_ects.exception;

public class CourseAlreadyExistException extends Exception {

    public CourseAlreadyExistException() {
        System.out.println("!!!Impossible d'enregistrer une matière avec un nom déjà existant!!!");
    }
}
