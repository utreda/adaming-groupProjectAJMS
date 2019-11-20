package com.adaming.groupprojectajms.gestion_ects.exception;

public class NullCourseException extends Exception {

    public NullCourseException() {
        System.out.println("!!!Impossible d'enregistrer une matière nulle!!!");
    }
}
