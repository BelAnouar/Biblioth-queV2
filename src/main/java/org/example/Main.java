package org.example;


import org.example.presentation.impl.ConsoleUI;
import org.example.presentation.MainGui;

public class Main {

    public static void main(String[] args) {
        MainGui mainGui = new ConsoleUI();
        mainGui.start();

    }

}