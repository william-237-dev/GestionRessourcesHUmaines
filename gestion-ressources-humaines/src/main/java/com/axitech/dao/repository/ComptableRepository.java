package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.BulletinPaie;
import com.axitech.dao.entities.Comptable;

public class ComptableRepository {
    private Connection connection;
    private Comptable comptable = new Comptable();
    private List<BulletinPaie> bulletinsPaie = new ArrayList<>();

    public ComptableRepository() {
    }

    

    public ComptableRepository(Connection connection, Comptable comptable, List<BulletinPaie> bulletinsPaie) {
        this.connection = connection;
        this.comptable = comptable;
        this.bulletinsPaie = bulletinsPaie;
    }

    
    


    public ComptableRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }



    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    public Comptable getComptable() {
        return comptable;
    }



    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }



    public List<BulletinPaie> getBulletinsPaie() {
        return bulletinsPaie;
    }



    public void setBulletinsPaie(List<BulletinPaie> bulletinsPaie) {
        this.bulletinsPaie = bulletinsPaie;
    }



    public void save(Comptable comptable) {
        String sql = "INSERT INTO Comptable (nom, prenom, adresse) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, comptable.getNom());
            pstatement.setString(2, comptable.getPrenom());
            pstatement.setString(3, comptable.getAdresse());
            pstatement.executeUpdate();
            System.out.println("Comptable saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving Comptable: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listeComptable() {
        String sql = "SELECT * FROM Comptable";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des Comptables :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                String nom = pstatement.getResultSet().getString("nom");
                String prenom = pstatement.getResultSet().getString("prenom");
                String adresse = pstatement.getResultSet().getString("adresse");
                System.out.println("info : " + id + "  \n" + nom + "    " + prenom + "  \n" + adresse);
            }
        } catch (SQLException e) {
            System.out.println("Error listing Comptables: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
}
