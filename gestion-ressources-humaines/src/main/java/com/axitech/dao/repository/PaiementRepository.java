package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaiementRepository {
    private Connection connection;

    public PaiementRepository(Connection connection) {
        this.connection = connection;
    }

    public void listePaiement() {
        String sql = "SELECT * FROM paiement";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des paiements :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getDouble("montant")
                        + "  \n" + rs.getString("datePaiement") + "  (drh_id=" + rs.getInt("drh_id") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error listing paiements: " + e.getMessage());
        }
    }
}