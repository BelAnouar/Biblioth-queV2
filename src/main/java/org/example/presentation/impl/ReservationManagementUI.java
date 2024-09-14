package org.example.presentation.impl;

import org.example.Enum.DocumentType;
import org.example.presentation.MainGui;

import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.utils.Print.logInfo;

public class ReservationManagementUI implements MainGui {
    @Override
    public void start() {

    }

    @Override
    public void printMenuHeader() {
        logInfo("***** MENU DE GESTION  Reservation *****");
        logInfo("1. Reserver document");
        logInfo("2. anuller un document");
        logInfo("3. Quitter");
    }

    public void retournerDocument(Scanner sc) {
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
