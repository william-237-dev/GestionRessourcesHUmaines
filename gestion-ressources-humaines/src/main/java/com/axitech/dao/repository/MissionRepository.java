package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MissionRepository {
    private Connection connection;

    public MissionRepository(Connection connection) {
        this.connection = connection;
    }

    public void listeMission() {
        String sql = "SELECT * FROM mission";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des missions :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("description")
                        + "  \n" + rs.getDate("date") + "  (drh_id=" + rs.getInt("drh_id") + ")");
            }
        } catch (SQLException e) {
            System.out.println("Error listing missions: " + e.getMessage());
        }
    }
}