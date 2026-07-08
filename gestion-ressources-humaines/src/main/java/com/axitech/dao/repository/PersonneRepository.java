package com.axitech.dao.repository;

import java.sql.Connection;

import com.axitech.dao.entities.Comptable;
import com.axitech.dao.entities.Pdg;
import com.axitech.dao.entities.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PersonneRepository {
    private Connection connection;
    private Personne personne = new Personne();
   
    public PersonneRepository() {
    } 
    

    public PersonneRepository(Connection connection, Personne personne) {
        this.connection = connection;
        this.personne = personne;

    }  

    
    public PersonneRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }


    public Personne getPersonne() {
        return personne;
    }


    public int setPersonne(Personne personne) {
        this.personne = personne;
        return 0;
    }


    public void  save(Personne personne2) {
        String sql = "INSERT INTO personne (nom, prenom, adresse) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, personne2.getNom());
            pstatement.setString(2, personne2.getPrenom());
            pstatement.setString(3, personne2.getAdresse());
            pstatement.executeUpdate();
            System.out.println("Employer saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving employer: " + e.getMessage());
            e.printStackTrace();
        }
    
    }

    public void listemployer(){
        String sql = "SELECT * FROM personne";
        try {
            Statement stmt =connection.createStatement();
           ResultSet rs= stmt.executeQuery(sql);

           while(rs.next()){
            int id = rs.getInt("id");
            String nom = rs.getString("nom");
            String prenom= rs.getString("prenom");
            String adresse = rs.getString("adresse");
            System.out.println("info : " +id +"  \n" +nom+ "    " +prenom+"  \n"+adresse);

           }
        } catch (SQLException e) {
            System.out.println("Error listing employers: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public int insererPersonne(Personne pdg, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insererPersonne'");
    }


    public static List<Personne> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

} 
