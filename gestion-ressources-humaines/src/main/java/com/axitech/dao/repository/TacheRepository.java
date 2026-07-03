package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.axitech.dao.entities.Tache;

public class TacheRepository {
    private Connection connection;
    private Tache tache = new Tache();

    public TacheRepository() {
    }

    public TacheRepository(Connection connection, Tache tache) {
        this.connection = connection;
        this.tache = tache;
    }


     
    public TacheRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public void save(Tache tache) {
        String sql = "INSERT INTO Tache (description, date) VALUES (?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, tache.getDescription());
            pstatement.setDate(2, tache.getDate());
            pstatement.executeUpdate();
            System.out.println("Tache saved successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving tache: " + e.getMessage());
        }
    }

    public void listeTache() {
        String sql = "SELECT * FROM Tache";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet resultSet = pstatement.executeQuery();
            System.out.println("Liste des taches :");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                System.out.println("info : " + id + "  \n" + description + "  \n" + date);
            }
        } catch (SQLException e) {
            System.out.println("Error listing taches: " + e.getMessage());
        }
    }
}
