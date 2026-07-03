package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.BulletinPaie;
import com.axitech.dao.entities.Employe;

public class BulletinPaieRepository {
    private Connection connection;
    private BulletinPaie bulletinPaie = new BulletinPaie();
    private List<Employe> employes = new ArrayList<>(); 

    
    public BulletinPaieRepository() {
    }

    

    public BulletinPaieRepository(Connection connection, BulletinPaie bulletinPaie, List<Employe> employes) {
        this.connection = connection;
        this.bulletinPaie = bulletinPaie;
        this.employes = employes;
    }


    

    

    public BulletinPaieRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }



    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    public BulletinPaie getBulletinPaie() {
        return bulletinPaie;
    }



    public void setBulletinPaie(BulletinPaie bulletinPaie) {
        this.bulletinPaie = bulletinPaie;
    }



    public List<Employe> getEmployes() {
        return employes;
    }



    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }



    public void save(BulletinPaie bulletinPaie) {
        String sql = "INSERT INTO BulletinPaie (id_employe, nom_employe) VALUES (?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, bulletinPaie.getId());
            pstatement.setString(2, bulletinPaie.getNom_employe());
            pstatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erreur lors de l'insertion du bulletin de paie : " + e.getMessage());
        }
    }
    
    public void listeBulletinPaie() {
        String sql = "SELECT * FROM BulletinPaie";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des bulletins de paie :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                int id_employe = pstatement.getResultSet().getInt("id_employe");
                String nom_employe = pstatement.getResultSet().getString("nom_employe");
                System.out.println("info : " + id + "  \n" + id_employe + "    " + nom_employe);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération des bulletins de paie : " + e.getMessage());
        }
    }
}
