package com.adaming.groupprojectajms.gestion_ects.exception;

public class NullUserException extends Exception {

    public NullUserException() {
        System.out.println("!!!Impossible d'enregistrer un élève ou un professeur nul!!!");
    }
}
