package org.example.presentation.impl;

import org.example.Enum.DocumentType;
import org.example.Enum.UtilisateurType;
import org.example.metier.JournalScientifique;
import org.example.metier.Livre;
import org.example.metier.Magasine;
import org.example.metier.TheseUniversitaire;
import org.example.metier.abstracts.Documents;
import org.example.service.DocumentService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.example.presentation.MainGui;

import static org.example.utils.Print.logInfo;

public class DocumentManagementUI implements MainGui {

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        MainGui menuToNavigate = null;
        while (true) {
            printMenuHeader();
            System.out.print("Choisissez une option : ");

            int option;

            try {
                option = Integer.parseInt(sc.next());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                continue;
            }

            switch (option) {
                case 1:
                    ajouterDocument(sc);
                    break;
                case 2:
                    afficherDocuments(sc);
                    break;
                case 3:
                    rechercherDocument(sc);
                    break;
                case 4:
                    modifierDocument(sc);
                    break;
                case 5:
                    supprimerDocument(sc);
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
        logInfo("***** MENU DE GESTION DES DOCUMENTS *****");
        logInfo("1. Ajouter un document");
        logInfo("2. Afficher tous les documents");
        logInfo("3. Rechercher un document");
        logInfo("4. Modifier un document");
        logInfo("5. Supprimer un document");
        logInfo("6.Return to mainMenu");
    }
    private void supprimerDocument(Scanner sc) {

        logInfo("Type de document (1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire):");

        int ch = sc.nextInt();
        sc.nextLine();
        logInfo("entrer id:");
        int id = Integer.parseInt(sc.nextLine());
        DocumentType type = getDocumentType(ch);

        DocumentService.delete(id,type);
    }
    private void afficherDocuments(Scanner sc) {
        logInfo("Type de document (1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire):");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);

        DocumentService.afficherTous(type);
    }

    private void modifierDocument(Scanner sc) {
        logInfo("Type de document (1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire):");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);
        logInfo("enter id:");
        int id = sc.nextInt();
        logInfo("author");
        String author = sc.nextLine();
        logInfo("title");
        String title = sc.nextLine();
        logInfo("local");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datePublication = LocalDate.parse(sc.nextLine(), df);
        logInfo("nomnede");
        int nombreDePages = sc.nextInt();
        logInfo("acess");
        UtilisateurType acces = UtilisateurType.valueOf(sc.next());

        System.out.println(id);

        Documents document;
        switch (type) {
            case LIVRE:
                logInfo("isbn");
                int isbn = sc.nextInt();
                document = new Livre(id,author, title, datePublication, nombreDePages, acces, isbn);
                break;
            case MAGAZINE:
                logInfo("numero");
                int numero = sc.nextInt();
                document = new Magasine(id,author, title, datePublication, nombreDePages, acces, numero);
                break;
            case JOURNAL_SCIENTIFIQUE:
                logInfo("domaineRec");
                String domaineRec = sc.next();
                document = new JournalScientifique(id,author, title, datePublication, nombreDePages, acces, domaineRec);
                break;
            case THESE_UNIVERSITAIRE:
                logInfo("uni");
                String universite = sc.next();
                logInfo("domaine");
                String domaine = sc.next();
                document = new TheseUniversitaire(id,author, title, datePublication, nombreDePages, acces, universite, domaine);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        DocumentService.update(document, type);
    }





    public void ajouterDocument(Scanner sc) {
        logInfo("Type de document (1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire):");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);
        logInfo("author");
        String author = sc.nextLine();
        logInfo("author");
        String title = sc.nextLine();
        logInfo("local");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datePublication = LocalDate.parse(sc.nextLine(), df);
        logInfo("nomnede");
        int nombreDePages = sc.nextInt();
        logInfo("acess");
        UtilisateurType acces = UtilisateurType.valueOf(sc.next());



        Documents document;
        switch (type) {
            case LIVRE:
                logInfo("isbn");
                int isbn = sc.nextInt();
                document = new Livre(author, title, datePublication, nombreDePages, acces, isbn);
                break;
            case MAGAZINE:
                logInfo("numero");
                int numero = sc.nextInt();
                document = new Magasine(author, title, datePublication, nombreDePages, acces, numero);
                break;
            case JOURNAL_SCIENTIFIQUE:
                logInfo("domaineRec");
                String domaineRec = sc.next();
                document = new JournalScientifique(author, title, datePublication, nombreDePages, acces, domaineRec);
                break;
            case THESE_UNIVERSITAIRE:
                logInfo("uni");
                String universite = sc.next();
                logInfo("domaine");
                String domaine = sc.next();
                document = new TheseUniversitaire(author, title, datePublication, nombreDePages, acces, universite, domaine);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        DocumentService.ajouter(document, type);


    }



    public void rechercherDocument(Scanner sc) {
        logInfo("1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire:");
        try {
            int ch = sc.nextInt();
            sc.nextLine();
            DocumentType type = getDocumentType(ch);
        } catch (InputMismatchException e) {
            logInfo("Invalid input. Please enter the correct data type.");

        }
    }

    private DocumentType getDocumentType(int choice) {
        return switch (choice) {
            case 1 -> DocumentType.LIVRE;
            case 2 -> DocumentType.MAGAZINE;
            case 3 -> DocumentType.JOURNAL_SCIENTIFIQUE;
            case 4 -> DocumentType.THESE_UNIVERSITAIRE;
            default -> throw new InputMismatchException("Invalid Document Type");
        };
    }

}