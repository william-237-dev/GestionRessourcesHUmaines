package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.axitech.dao.entities.Mission;

public class MissionRepository {
    private Connection connection;
    private Mission mission = new Mission();
    
    public MissionRepository() {
    }

    public MissionRepository(Connection connection, Mission mission) {
        this.connection = connection;
        this.mission = mission;
    }



     public MissionRepository(Connection connection) {
        this.connection = connection;
    }


    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public void save(Mission mission) {
        String sql = "INSERT INTO Mission (description, date) VALUES (?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, mission.getDescription());
            pstatement.setDate(2, mission.getDate());
            pstatement.executeUpdate();
            System.out.println("Mission saved successfully.");
        } catch (SQLException e) {
            System.out.println("Error saving mission: " + e.getMessage());
        }
    }

    public void listeMission() {
        String sql = "SELECT * FROM Mission";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet resultSet = pstatement.executeQuery();
            System.out.println("Liste des missions :");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                Date date = resultSet.getDate("date");
                System.out.println("info : " + id + "  \n" + description + "  \n" + date);
            }
        } catch (SQLException e) {
            System.out.println("Error listing missions: " + e.getMessage());
        }
    }
}
