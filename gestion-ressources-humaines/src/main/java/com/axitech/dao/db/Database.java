package com.axitech.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private String url = "jdbc:mysql://172.17.0.2:3306/gestion_des_r_h";
    private String username = "root";
    private String password = "Q1W2E3R4T5Y6";

    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion a la bd réussie");
        } catch (Exception e) {
            System.out.println("erreur de connexion a la bd :"+e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

   
}
