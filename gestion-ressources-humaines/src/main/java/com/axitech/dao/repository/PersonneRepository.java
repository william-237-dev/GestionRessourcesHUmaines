package com.axitech.dao.repository;

import com.axitech.dao.db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.axitech.dao.entities.Personne;

public class PersonneRepository {
    private Connection connection;
    private Personne personne = new Personne();
    private Database database;

    public PersonneRepository() throws ClassNotFoundException {
        this.database = new Database();
        this.connection = database.getConnection();
    }

    public PersonneRepository(Connection connection, Personne personne) {
        this.connection = connection;
        this.personne = personne;
    }



    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /** Insere une Personne et renvoie l'id genere (utile pour l'heritage : employe/pdg/drh/comptable reutilisent cet id). */
    public void save(Personne personne) {
        Connection connect = this.connection;

        String sql = "INSERT INTO personne (nom, prenom, adresse) VALUES (?, ?, ?)";
        try (PreparedStatement pStatement = connect.prepareStatement(sql)) {
            pStatement.setString(1, personne.getNom());
            pStatement.setString(2, personne.getPrenom());
            pStatement.setString(3, personne.getAdresse());
            pStatement.executeUpdate();
            System.out.println("excecution de la methode save");
            System.out.println("succes");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        
      
    }

    public List<Personne> findAll() {
        String sql = "SELECT * FROM personne";
        List<Personne> personnes = new java.util.ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Liste des personnes :");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String adresse = rs.getString("adresse");
                System.out.println("info : " + id + "  \n" + nom + "    " + prenom + "  \n" + adresse);
                Personne p = new Personne();
                p.setId(id);
                p.setNom(nom);
                p.setPrenom(prenom);
                p.setAdresse(adresse);
                personnes.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personnes;
    }

    public int insererPersonne(Personne pdg) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insererPersonne'");
    }

    public void insererPersonne(Personne drh, String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insererPersonne'");
    }
}