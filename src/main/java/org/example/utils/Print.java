package org.example.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Print {
    private static final Logger LOGGER = Logger.getLogger(Print.class.getName());

    public static void logInfo(String message) {

        LOGGER.log(Level.INFO, message);
    }


}