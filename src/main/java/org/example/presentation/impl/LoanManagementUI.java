package org.example.presentation.impl;

import org.example.Enum.DocumentType;
import org.example.presentation.MainGui;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.utils.Print.logInfo;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);

        logInfo("Entrez le titre du document à emprunter :");
        String title = sc.nextLine();

    }

    public void retournerDocument(Scanner sc) {
        logInfo("1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire:");

        int ch = sc.nextInt();
        sc.nextLine();
        DocumentType type = getDocumentType(ch);

        logInfo("Entrez le titre du document à retourner :");
        String title = sc.nextLine();

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
