package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.BulletinPaie;
import com.axitech.dao.entities.Employe;
import com.axitech.dao.entities.Paiement;

public class PaiementRepository {
    private Connection connection;
    private Paiement paiement = new Paiement();
    private List<BulletinPaie> bulletinPaieList = new ArrayList<>();
    private List<Employe> employeList = new ArrayList<>();

    public PaiementRepository() {
    }

    public PaiementRepository(Connection connection, Paiement paiement, List<BulletinPaie> bulletinPaieList, List<Employe> employeList) {
        this.connection = connection;
        this.paiement = paiement;
        this.bulletinPaieList = bulletinPaieList;
        this.employeList = employeList;
    }


    public PaiementRepository(Connection connection) {
        this.connection = connection;
    }
    

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Paiement getPaiement() {
        return paiement;
    }

    public void setPaiement(Paiement paiement) {
        this.paiement = paiement;
    }

    public List<BulletinPaie> getBulletinPaieList() {
        return bulletinPaieList;
    }

    public void setBulletinPaieList(List<BulletinPaie> bulletinPaieList) {
        this.bulletinPaieList = bulletinPaieList;
    }

    public List<Employe> getEmployeList() {
        return employeList;
    }

    public void setEmployeList(List<Employe> employeList) {
        this.employeList = employeList;
    }
    

    public void save(Paiement paiement) {
        String sql = "INSERT INTO Paiement (montant, datePaiement) VALUES (?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setDouble(1, paiement.getMontant());
            pstatement.setString(2, paiement.getDatePaiement());
            pstatement.executeUpdate();
            System.out.println("Paiement saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving Paiement: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listePaiement() {
        String sql = "SELECT * FROM Paiement";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet resultSet = pstatement.executeQuery();
            System.out.println("Liste des paiements :");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                double montant = resultSet.getDouble("montant");
                String datePaiement = resultSet.getString("datePaiement");
                System.out.println("info : " + id + "  \n" + montant + "  \n" + datePaiement);
            }
        } catch (SQLException e) {
            System.out.println("Error listing paiements: " + e.getMessage());
        }
    }
    
}
