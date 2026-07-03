package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.axitech.dao.entities.Drh;
import com.axitech.dao.entities.Mission;
import com.axitech.dao.entities.Paiement;

public class DrhRepository {
    private Connection connection;
    private List<Mission> missions = new ArrayList<>();
    private List<Paiement> paiements = new ArrayList<>();
    private Drh drh = new Drh();

    public DrhRepository() {
    }

    

    public DrhRepository(Connection connection, List<Mission> missions, List<Paiement> paiements, Drh drh) {
        this.connection = connection;
        this.missions = missions;
        this.paiements = paiements;
        this.drh = drh;
    }

    
    public DrhRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }



    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    public List<Mission> getMissions() {
        return missions;
    }



    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }



    public List<Paiement> getPaiements() {
        return paiements;
    }



    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }



    public Drh getDrh() {
        return drh;
    }



    public void setDrh(Drh drh) {
        this.drh = drh;
    }



    public void save(Drh drh) {
        String sql = "INSERT INTO Drh (nom, prenom, adresse) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, drh.getNom());
            pstatement.setString(2, drh.getPrenom());
            pstatement.setString(3, drh.getAdresse());
            pstatement.executeUpdate();
            System.out.println("DRH saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving DRH: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listeDrh() {
        String sql = "SELECT * FROM Drh";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des DRH :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                String nom = pstatement.getResultSet().getString("nom");
                String prenom = pstatement.getResultSet().getString("prenom");
                String adresse = pstatement.getResultSet().getString("adresse");
                System.out.println("info : " + id + "  \n" + nom + "    " + prenom + "  \n" + adresse);
            }
        } catch (SQLException e) {
            System.out.println("Error listing DRH: " + e.getMessage());
            e.printStackTrace();
        }
    }

    
}
