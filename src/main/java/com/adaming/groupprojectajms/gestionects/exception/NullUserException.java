package com.adaming.groupprojectajms.gestionects.exception;

public class NullUserException extends Exception {

    public NullUserException() {
        System.out.println("!!!Impossible d'enregistrer un élève ou un professeur nul!!!");
    }
}
