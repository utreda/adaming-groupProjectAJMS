package com.adaming.groupprojectajms.gestionects.exception;

public class UserAlreadyExistException extends Exception {

    public UserAlreadyExistException() {
        System.out.println("!!!Impossible d'enregistrer un professeur ou un élève avec un e-mail déjà existant!!!");
    }
}
