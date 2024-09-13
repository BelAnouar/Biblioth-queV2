package org.example.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataUtils
{
    private static DataUtils instance;
    private Connection connection;
    private  String url ;
    private String username ;
    private String password ;
    private final Dotenv dotenv = Dotenv.configure().load();



    private DataUtils() throws SQLException {
        try {
            url = dotenv.get("DB_URL");
            username = dotenv.get("DB_USERNAME");
            password = dotenv.get("DB_PASSWORD");
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DataUtils getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataUtils();
        } else if (instance.getConnection().isClosed()) {
            instance = new DataUtils();
        }

        return instance;
    }



}