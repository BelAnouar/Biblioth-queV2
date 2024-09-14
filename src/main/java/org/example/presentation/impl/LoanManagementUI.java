package org.example.presentation.impl;

import org.example.Enum.DocumentType;
import org.example.presentation.MainGui;
import org.example.service.LoanService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.utils.Print.logInfo;



public class LoanManagementUI implements MainGui {

    @Override
    public void start() {
        Scanner sc = new Scanner(System.in);
        MainGui menuToNavigate = null;
        while (true) {
            printMenuHeader();
            System.out.print("Choisissez une option : ");

            int option;
            try {
                option = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un numéro.");
                continue;
            }

            switch (option) {
                case 1:
                    emprunterDocument(sc);
                    break;
                case 2:
                    retournerDocument(sc);
                    break;
                case 3:
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
        logInfo("***** MENU DE GESTION DES EMPRUNTS *****");
        logInfo("1. EMPRUNTS  document");
        logInfo("2. Return  document");
        logInfo("3. Quitter");
    }

    public void emprunterDocument(Scanner sc) {
        logInfo("1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire:");

        try {
            int ch = sc.nextInt();
            sc.nextLine();
            DocumentType type = getDocumentType(ch);
            logInfo("Entrez l'ID de la enprunter :");
            int id = sc.nextInt();


            LoanService loanService = new LoanService();
            loanService.emprunter(id,type);

        } catch (InputMismatchException e) {
            logInfo("Entrée invalide. Veuillez entrer un ID de réservation valide.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void retournerDocument(Scanner sc) {
        logInfo("1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire:");

        try {
            int ch = sc.nextInt();
            sc.nextLine();
            DocumentType type = getDocumentType(ch);
            logInfo("Entrez l'ID de la enprunter pour annuler :");
            int id = sc.nextInt();


            LoanService loanService = new LoanService();
            loanService.retourner(id,type);

        } catch (InputMismatchException e) {
            logInfo("Entrée invalide. Veuillez entrer un ID de réservation valide.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private DocumentType getDocumentType(int choice) {
        return switch (choice) {
            case 1 -> DocumentType.LIVRE;
            case 2 -> DocumentType.MAGAZINE;
            case 3 -> DocumentType.JOURNAL_SCIENTIFIQUE;
            case 4 -> DocumentType.THESE_UNIVERSITAIRE;
            default -> throw new InputMismatchException("Type de document invalide");
        };
    }
}