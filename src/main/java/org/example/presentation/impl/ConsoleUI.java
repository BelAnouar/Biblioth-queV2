package org.example.presentation.impl;

import org.example.presentation.MainGui;

import java.util.Scanner;

import static org.example.utils.Print.logInfo;

public class ConsoleUI implements MainGui {
    private static final String MAIN_MENU_TEXT = "Please, enter number in console to proceed." + System.lineSeparator()
            + "1. Document Management" + System.lineSeparator()
            + "2. User Management" + System.lineSeparator()
            + "3. Loan Management" + System.lineSeparator()
            + "4. Reservation Management" + System.lineSeparator()
            + "5. Exit";
    @Override
    public void start() {
        while (true) {
            printMenuHeader();
            MainGui menuToNavigate = null;
            Scanner sc = new Scanner(System.in);
            logInfo("Options: ");
            int commandNumber = sc.nextInt();


                switch (commandNumber) {
                    case 1:
                       menuToNavigate = new DocumentManagementUI();
                        break;
                    case 2:
                          menuToNavigate=new UserManagementUI();
                        break;
                    case 3:
                       menuToNavigate=new LoanManagementUI();
                        break;
                    case 4:
                        menuToNavigate=new ReservationManagementUI();
                        break;
                    case 5:
                        return;
                    default:
                        logInfo("Invalid option. Please choose a number between 1 and 6.");
                        break;
                }
                menuToNavigate.start();
            }

    }

    @Override
    public void printMenuHeader() {
        logInfo("***** MAIN MENU *****");
        logInfo(MAIN_MENU_TEXT);
    }

}