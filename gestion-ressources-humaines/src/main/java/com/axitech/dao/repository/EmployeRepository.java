package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.Contrat;
import com.axitech.dao.entities.Employe;
import com.axitech.dao.entities.Formation;
import com.axitech.dao.entities.Tache;
import com.axitech.dao.entities.TypeConge;

public class EmployeRepository {
    private Connection connection;
    private Employe employer = new Employe();
    private List<TypeConge> typeConges = new ArrayList<>();
    private List<Tache> taches = new ArrayList<>();
    private List<Contrat> contrats = new ArrayList<>();
    private List<Formation> formations = new ArrayList<>();


    public EmployeRepository() {
    }

    

      public EmployeRepository(Connection connection, Employe employer, List<TypeConge> typeConges, List<Tache> taches,
            List<Contrat> contrats, List<Formation> formations) {
        this.connection = connection;
        this.employer = employer;
        this.typeConges = typeConges;
        this.taches = taches;
        this.contrats = contrats;
        this.formations = formations;
     }



      public EmployeRepository(Connection connection) {
        this.connection = connection;
    }



      public Connection getConnection() {
        return connection;
    }



      public void setConnection(Connection connection) {
          this.connection = connection;
      }



      public Employe getEmployer() {
          return employer;
      }



      public void setEmployer(Employe employer) {
          this.employer = employer;
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



      public List<Formation> getFormations() {
          return formations;
      }



      public void setFormations(List<Formation> formations) {
          this.formations = formations;
      }



      public void  save(Employe employe) {
        String sql = "INSERT INTO Employe (nom, prenom, adresse) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, employe.getNom());
            pstatement.setString(2, employe.getPrenom());
            pstatement.setString(3, employe.getAdresse());
            pstatement.executeUpdate();
            System.out.println("Employer saved successfully."); 
        } catch (SQLException e) {
            System.out.println("Error saving employer: " + e.getMessage());
            e.printStackTrace();
        }
    
    }

    public void listemployer(){
        String sql = "SELECT * FROM Employe";
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
}
