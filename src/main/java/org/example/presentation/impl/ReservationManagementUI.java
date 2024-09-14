package org.example.presentation.impl;

import org.example.Enum.DocumentType;
import org.example.presentation.MainGui;
import org.example.service.ReservationService;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static org.example.utils.Print.logInfo;

public class ReservationManagementUI implements MainGui {
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
                    reserverDocument(sc);
                    break;
                case 2:
                    annulerReservation(sc);
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
        if (menuToNavigate != null) {
            menuToNavigate.start();
        }
    }

    @Override
    public void printMenuHeader() {
        logInfo("***** MENU DE GESTION RESERVATION *****");
        logInfo("1. Reserver document");
        logInfo("2. Annuler une réservation");
        logInfo("3. Quitter");
    }

    public void reserverDocument(Scanner sc) {
        logInfo("1. Livre, 2. Magazine, 3. JournalScientifique, 4. TheseUniversitaire:");
        try {
            int ch = sc.nextInt();
            sc.nextLine();
            DocumentType type = getDocumentType(ch);

            logInfo("Entrez l'ID du document à réserver:");
            int documentId = sc.nextInt();
            sc.nextLine();

            logInfo("Entrez l'ID de l'utilisateur:");
            int userId = sc.nextInt();
            sc.nextLine();

            ReservationService reservationService = new ReservationService();
            boolean success = reservationService.reserverDocument(documentId, userId, type);
            if (success) {
                logInfo("Document réservé avec succès.");
            } else {
                logInfo("Échec de la réservation. Le document n'est peut-être pas disponible.");
            }
        } catch (InputMismatchException e) {
            logInfo("Entrée invalide. Veuillez entrer les données correctes.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void annulerReservation(Scanner sc) {
        try {
            logInfo("Entrez l'ID de la réservation à annuler:");
            int reservationId = sc.nextInt();


            ReservationService reservationService = new ReservationService();
            reservationService.annulerReservation(reservationId);

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