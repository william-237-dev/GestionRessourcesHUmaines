package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.Contrat;
import com.axitech.dao.entities.Pdg;
import com.axitech.dao.entities.Tache;
import com.axitech.dao.entities.TypeConge;

public class PdgRepository {
    private Connection connection;
    private Pdg pdg = new Pdg();
    private List<TypeConge> typeConges = new ArrayList<>();
    private List<Tache> taches = new ArrayList<>();
    private List<Contrat> contrats = new ArrayList<>();
    
    public PdgRepository() {
    }

    public PdgRepository(Connection connection, Pdg pdg, List<TypeConge> typeConges, List<Tache> taches,
            List<Contrat> contrats) {
        this.connection = connection;
        this.pdg = pdg;
        this.typeConges = typeConges;
        this.taches = taches;
        this.contrats = contrats;
    }


    public PdgRepository(Connection connection) {
        this.connection = connection;
    }
    
    
    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Pdg getPdg() {
        return pdg;
    }

    public void setPdg(Pdg pdg) {
        this.pdg = pdg;
    }

    public List<TypeConge> getTypeConges() {
        return typeConges;
    }

    public void setTypeConges(List<TypeConge> typeConges) {
        this.typeConges = typeConges;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }

    

    public void save(Pdg pdg) {
        String sql = "INSERT INTO Pdg (nom, prenom, adresse) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, pdg.getNom());
            pstatement.setString(2, pdg.getPrenom());
            pstatement.setString(3, pdg.getAdresse());
            pstatement.executeUpdate();
            System.out.println("PDG saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving PDG: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void listePdg() {
        String sql = "SELECT * FROM Pdg";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des PDG :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                String nom = pstatement.getResultSet().getString("nom");
                String prenom = pstatement.getResultSet().getString("prenom");
                String adresse = pstatement.getResultSet().getString("adresse");
                System.out.println("info : " + id + "  \n" + nom + "    " + prenom + "  \n" + adresse);
            }
        } catch (SQLException e) {
            System.out.println("Error listing PDG: " + e.getMessage());
            e.printStackTrace();
        }
    }

}