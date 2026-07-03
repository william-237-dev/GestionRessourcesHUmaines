package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.axitech.dao.entities.Formation;

public class FormationRepository { 
    private Connection connection;
    private Formation formation = new Formation();
   
   
    public FormationRepository() {
    }

    public FormationRepository(Connection connection, Formation formation) {
        this.connection = connection;
        this.formation = formation;
    }



     public FormationRepository(Connection connection) {
        this.connection = connection;
    }




    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public void save(Formation formation) {
        String sql = "INSERT INTO formation (description) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, formation.getDescription());
            statement.executeUpdate();
            System.out.println("Formation insérée avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'insertion de la formation : " + e.getMessage());
        }
    }

    public void listeFormation() {
        String sql = "SELECT * FROM formation";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeQuery();
            System.out.println("Liste des formations :");
            while (statement.getResultSet().next()) {
                int id = statement.getResultSet().getInt("id");
                String description = statement.getResultSet().getString("description");
                System.out.println("info : " + id + "  \n" + description);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération des formations : " + e.getMessage());
        }
    }
    
    
    
}
