package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.axitech.dao.entities.Formation;

public class FormationRepository {
    private Connection connection;

    public FormationRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(Formation formation) {
        String sql = "INSERT INTO formation (description) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstatement.setString(1, formation.getDescription());
            pstatement.executeUpdate();
            System.out.println("Formation inseree avec succes !");

            ResultSet keys = pstatement.getGeneratedKeys();
            return keys.next() ? keys.getInt(1) : -1;
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la formation : " + e.getMessage());
            return -1;
        }
    }

    public void listeFormation() {
        String sql = "SELECT * FROM formation";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des formations :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("description"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des formations : " + e.getMessage());
        }
    }
}