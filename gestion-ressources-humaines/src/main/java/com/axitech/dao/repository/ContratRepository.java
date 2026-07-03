package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.axitech.dao.entities.Contrat;

public class ContratRepository {
    private Connection connection;
    private Contrat contrat = new Contrat();
    
    public ContratRepository() {
    }

    public ContratRepository(Connection connection, Contrat contrat) {
        this.connection = connection;
        this.contrat = contrat;
    }


    



    public ContratRepository(Connection connection) {
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public void save(Contrat contrat) {
        String sql = "INSERT INTO Contrat (description) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, contrat.getDescription());
            pstatement.executeUpdate();
            System.out.println("Contrat ajouté avec succès");
        } catch (Exception e) {
            System.out.println("Erreur lors de l'ajout du contrat : " + e.getMessage());
        }
    }

    public void listeContrat() {
        String sql = "SELECT * FROM Contrat";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des contrats :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                String description = pstatement.getResultSet().getString("description");
                System.out.println("info : " + id + "  \n" + description);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des contrats : " + e.getMessage());
        }
    }
    
}
