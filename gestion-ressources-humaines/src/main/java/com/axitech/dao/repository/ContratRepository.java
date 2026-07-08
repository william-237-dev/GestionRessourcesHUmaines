package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.axitech.dao.entities.Contrat;

public class ContratRepository {
    private Connection connection;

    public ContratRepository(Connection connection) {
        this.connection = connection;
    }

    public void listeContrat() {
        String sql = "SELECT * FROM contrat";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des contrats :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("description")
                        + "  actif=" + rs.getBoolean("actif"));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recuperation des contrats : " + e.getMessage());
        }
    }

    public Contrat findById(int id) {
        String sql = "SELECT * FROM contrat WHERE id = ?";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            ResultSet rs = pstatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Contrat contrat = new Contrat(rs.getInt("id"), rs.getString("description"));
            contrat.setActif(rs.getBoolean("actif"));
            return contrat;
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement du contrat : " + e.getMessage());
            return null;
        }
    }
}