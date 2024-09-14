package org.example.presentation.impl;


import org.example.Enum.DocumentType;
import org.example.Enum.UtilisateurType;
import org.example.metier.*;
import org.example.metier.abstracts.Documents;
import org.example.metier.abstracts.Utilisateur;
import org.example.presentation.MainGui;
import org.example.service.DocumentService;
import org.example.service.UserService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.utils.Print.logInfo;

public class UserManagementUI implements MainGui {
    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        MainGui menuToNavigate = null;

        while (true) {
            printMenuHeader();
            System.out.print("Choisissez une option : ");

            int option;

            try {
                option = sc.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                continue;
            }

            switch (option) {
                case 1:
                    ajouterUtilisateur(sc);
                    break;
                case 2:
                    afficherUtilisateurs(sc);
                    break;
                case 3:
                    rechercherUtilisateur(sc);
                    break;
                case 4:
                    modifierUtilisateur(sc);
                    break;
                case 5:
                    supprimerUtilisateur(sc);
                    break;
                case 6:
                    menuToNavigate = new ConsoleUI();
                    break;
                default:
                    System.out.println("Option invalide. Essayez encore.");
                    break;
            }

            if (menuToNavigate != null) {
                break;
            }
        }
        menuToNavigate.start();
    }


    @Override
    public void printMenuHeader() {
        logInfo("***** MENU DE GESTION  User *****");
        logInfo("1. add user");
        logInfo("2. Afficher user");
        logInfo("3. Rechercher user");
        logInfo("4. Modifier user");
        logInfo("5. Supprimer user");
        logInfo("6.Return to mainMenu");
    }

    public void ajouterUtilisateur(Scanner sc) {
        logInfo("Type of User (1. Etudiant, 2. Professeur):");
        int ch = sc.nextInt();
        sc.nextLine();
        UtilisateurType type = getUtilisateurType(ch);
        String nom;
        String email;

        logInfo("Entrer le nom :");
        nom = sc.nextLine();

        logInfo("Entrer l'email :");
        email = sc.nextLine();

        Utilisateur utilisateur;

        switch (type) {
            case Etudiant:
                logInfo("Entrer le numéro d'étudiant :");
                String numero = sc.nextLine();
                utilisateur = new Etudiant(nom, email, numero);
                break;
            case Professeur:
                logInfo("Entrer le département :");
                String departement = sc.nextLine();
                utilisateur = new Professeur(nom, email, departement);
                break;


            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        UserService.ajouter(utilisateur, type);

    }

    public void afficherUtilisateurs(Scanner sc) {
        logInfo("Type of User (1. Etudiant, 2. Professeur):");
        try {

            int ch = sc.nextInt();
            sc.nextLine();
            UtilisateurType type = getUtilisateurType(ch);

            UserService.afficherTous(type);
        } catch (InputMismatchException e) {
            logInfo("Invalid input. Please enter the correct data type.");

        }
    }

    public void rechercherUtilisateur(Scanner sc) {
        try {
            int ch = sc.nextInt();
            sc.nextLine();
            UtilisateurType type = getUtilisateurType(ch);
            logInfo("Entrer id :");
            int id = sc.nextInt();

            UserService.search(id, type);
        } catch (InputMismatchException e) {
            logInfo("Invalid input. Please enter the correct data type.");

        }
    }

    public void modifierUtilisateur(Scanner sc) {
        logInfo("Type of User (1. Etudiant, 2. Professeur):");
        int ch = sc.nextInt();
        sc.nextLine();
        UtilisateurType type = getUtilisateurType(ch);
        int id;
        String nom;
        String email;
        logInfo("Entrer id :");
        id = sc.nextInt();
        logInfo("Entrer le nom :");
        nom = sc.nextLine();

        logInfo("Entrer l'email :");
        email = sc.nextLine();

        Utilisateur utilisateur;

        switch (type) {
            case Etudiant:
                logInfo("Entrer le numéro d'étudiant :");
                String numero = sc.nextLine();
                utilisateur = new Etudiant(id, nom, email, numero);
                break;
            case Professeur:
                logInfo("Entrer le département :");
                String departement = sc.nextLine();
                utilisateur = new Professeur(id, nom, email, departement);
                break;


            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        UserService.update(utilisateur, type);
    }

    public void supprimerUtilisateur(Scanner sc) {
        try {
            int ch = sc.nextInt();
            sc.nextLine();
            UtilisateurType type = getUtilisateurType(ch);
            logInfo("Entrer id :");
            int id = sc.nextInt();

            UserService.delete(id, type);
        } catch (InputMismatchException e) {
            logInfo("Invalid input. Please enter the correct data type.");

        }
    }


    private UtilisateurType getUtilisateurType(int choice) {
        return switch (choice) {
            case 1 -> UtilisateurType.Etudiant;
            case 2 -> UtilisateurType.Professeur;
            default -> throw new InputMismatchException("Type d'utilisateur invalide");
        };
    }

}
