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
                    addDocument(sc);
                    break;
                case 2:
                    displayDocuments(sc);
                    break;
                case 3:
                    searchDocument(sc);
                    break;
                case 4:
                    modifyDocument(sc);
                    break;
                case 5:
                    deleteDocument(sc);
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
    private void deleteDocument(Scanner sc) {
        logInfo("Document type (1. Book, 2. Magazine, 3. Scientific Journal, 4. University Thesis):");

        int ch = sc.nextInt();
        sc.nextLine();
        logInfo("Enter document ID:");
        int id = Integer.parseInt(sc.nextLine());
        DocumentType type = getDocumentType(ch);

        DocumentService.delete(id, type);
    }

    private void displayDocuments(Scanner sc) {
        logInfo("Document type (1. Book, 2. Magazine, 3. Scientific Journal, 4. University Thesis):");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);

        DocumentService.afficherTous(type);
    }

    private void modifyDocument(Scanner sc) {
        logInfo("Document type (1. Book, 2. Magazine, 3. Scientific Journal, 4. University Thesis):");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);
        logInfo("Enter ID:");
        int id = sc.nextInt();
        sc.nextLine();
        logInfo("Enter author:");
        String author = sc.nextLine();
        logInfo("Enter title:");
        String title = sc.nextLine();
        logInfo("Enter publication date (dd/MM/yyyy):");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datePublication = LocalDate.parse(sc.nextLine(), df);
        logInfo("Enter number of pages:");
        int numberOfPages = sc.nextInt();
        logInfo("Enter access level:");
        UtilisateurType access = UtilisateurType.valueOf(sc.next());

        Documents document;
        switch (type) {
            case LIVRE:
                logInfo("Enter ISBN:");
                int isbn = sc.nextInt();
                document = new Livre(id, author, title, datePublication, numberOfPages, access, isbn);
                break;
            case MAGAZINE:
                logInfo("Enter issue number:");
                int issueNumber = sc.nextInt();
                document = new Magasine(id, author, title, datePublication, numberOfPages, access, issueNumber);
                break;
            case JOURNAL_SCIENTIFIQUE:
                logInfo("Enter research domain:");
                String researchDomain = sc.next();
                document = new JournalScientifique(id, author, title, datePublication, numberOfPages, access, researchDomain);
                break;
            case THESE_UNIVERSITAIRE:
                logInfo("Enter university:");
                String university = sc.next();
                logInfo("Enter field of study:");
                String field = sc.next();
                document = new TheseUniversitaire(id, author, title, datePublication, numberOfPages, access, university, field);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        DocumentService.update(document, type);
    }






    public void addDocument(Scanner sc) {
        logInfo("Document type (1. Book, 2. Magazine, 3. Scientific Journal, 4. University Thesis):");

        int ch = sc.nextInt();
        sc.nextLine(); // Consume newline after the number input
        DocumentType type = getDocumentType(ch);
        logInfo("Enter author:");
        String author = sc.nextLine();
        logInfo("Enter title:");
        String title = sc.nextLine();
        logInfo("Enter publication date (dd/MM/yyyy):");
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datePublication = LocalDate.parse(sc.nextLine(), df);
        logInfo("Enter number of pages:");
        int numberOfPages = sc.nextInt();
        logInfo("Enter access level:");
        UtilisateurType access = UtilisateurType.valueOf(sc.next());

        Documents document;
        switch (type) {
            case LIVRE:
                logInfo("Enter ISBN:");
                int isbn = sc.nextInt();
                document = new Livre(author, title, datePublication, numberOfPages, access, isbn);
                break;
            case MAGAZINE:
                logInfo("Enter issue number:");
                int issueNumber = sc.nextInt();
                document = new Magasine(author, title, datePublication, numberOfPages, access, issueNumber);
                break;
            case JOURNAL_SCIENTIFIQUE:
                logInfo("Enter research domain:");
                String researchDomain = sc.next();
                document = new JournalScientifique(author, title, datePublication, numberOfPages, access, researchDomain);
                break;
            case THESE_UNIVERSITAIRE:
                logInfo("Enter university:");
                String university = sc.next();
                logInfo("Enter field of study:");
                String field = sc.next();
                document = new TheseUniversitaire(author, title, datePublication, numberOfPages, access, university, field);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }

        DocumentService.ajouter(document, type);
    }


    public void searchDocument(Scanner sc) {
        logInfo("1. Book, 2. Magazine, 3. Scientific Journal, 4. University Thesis:");
        try {
            int ch = sc.nextInt();
            sc.nextLine();
            DocumentType type = getDocumentType(ch);
            logInfo("Enter document ID:");
            int id = sc.nextInt();

            DocumentService.search(id, type);
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