package com.adaming.groupprojectajms.gestionects.exception;

public class CourseAlreadyExistException extends Exception {

    public CourseAlreadyExistException() {
        System.out.println("!!!Impossible d'enregistrer une matière avec un nom déjà existant!!!");
    }
}
