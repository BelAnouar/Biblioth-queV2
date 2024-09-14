package org.example.presentation.impl;

import org.example.presentation.MainGui;

import static org.example.utils.Print.logInfo;

public class UserManagementUI implements MainGui {
    @Override
    public void start() {

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
}
