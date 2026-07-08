package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TacheRepository {
    private Connection connection;

    public TacheRepository(Connection connection) {
        this.connection = connection;
    }

    public void listeTache() {
        String sql = "SELECT * FROM tache";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des taches :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("description")
                        + "  \n" + rs.getDate("date")
                        + "  (employe_id=" + rs.getObject("employe_id") + ", pdg_id=" + rs.getObject("pdg_id") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error listing taches: " + e.getMessage());
        }
    }
}