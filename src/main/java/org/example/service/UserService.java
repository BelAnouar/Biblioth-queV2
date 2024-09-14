package org.example.service;


import org.example.Enum.UtilisateurType;

import org.example.metier.*;
import org.example.metier.abstracts.Utilisateur;


import org.example.persistance.users.EtudiantDAO;
import org.example.persistance.users.ProfesseurDAO;

import java.sql.SQLException;


public class UserService {
   private  static final    EtudiantDAO etudiantDAO;
    private  static final    ProfesseurDAO professeurDAO ;


    static {
        try {
            etudiantDAO = new EtudiantDAO();
            professeurDAO=new ProfesseurDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }





    public static void ajouter(Utilisateur utilisateur, UtilisateurType type) {

        switch (type) {
            case Etudiant -> etudiantDAO.createUtilisateur((Etudiant) utilisateur);
            case Professeur -> professeurDAO.createUtilisateur((Professeur) utilisateur);

             }
    }

    public static void update(Utilisateur utilisateur, UtilisateurType type) {
        switch (type) {
            case Etudiant -> etudiantDAO.updateUtilisateur((Etudiant) utilisateur);
            case Professeur -> professeurDAO.updateUtilisateur((Professeur) utilisateur);
        }
    }

    public static void delete(int id, UtilisateurType type) {
        switch (type) {
            case Etudiant -> etudiantDAO.deleteUtilisateur(id);
            case Professeur -> professeurDAO.deleteUtilisateur(id);
        }
    }

    public static void afficherTous(UtilisateurType type) {
        switch (type) {
            case Etudiant -> etudiantDAO.displayAllUtilisateur().forEach(System.out::println);
            case Professeur -> professeurDAO.displayAllUtilisateur().forEach(System.out::println);
        }
    }

    public static void search(int id, UtilisateurType type) {
        switch (type) {
            case Etudiant -> System.out.println(etudiantDAO.displayUtilisateur(id));
            case Professeur -> System.out.println(professeurDAO.displayUtilisateur(id));
        }
    }
}
