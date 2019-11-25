package com.adaming.groupprojectajms.gestionects.exception;

public class NullCourseException extends Exception {

    public NullCourseException() {
        System.out.println("!!!Impossible d'enregistrer une matière nulle!!!");
    }
}
