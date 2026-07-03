package com.axitech.dao.repository;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

import com.axitech.dao.entities.Employe;
import com.axitech.dao.entities.TypeConge;

public class TypeCongeRepository {
    private Connection connection; 
    private TypeConge typeConge = new TypeConge();
    private List<Employe> employes = new ArrayList<>();

    public TypeCongeRepository() {
    }

    

    public TypeCongeRepository(Connection connection, TypeConge typeConge, List<Employe> employes) {
        this.connection = connection;
        this.typeConge = typeConge;
        this.employes = employes;
    }


    public TypeCongeRepository(Connection connection) {
        this.connection = connection;
    }



    public Connection getConnection() {
        return connection;
    }



    public void setConnection(Connection connection) {
        this.connection = connection;
    }



    public TypeConge getTypeConge() {
        return typeConge;
    }



    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }



    public List<Employe> getEmployes() {
        return employes;
    }



    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }



    public void save(TypeConge typeConge) {
        String sql = "INSERT INTO type_conge (type, nombre_jours, description) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, typeConge.getType());
            pstatement.setInt(2, typeConge.getNombre_jours());
            pstatement.setString(3, typeConge.getDescription());
            pstatement.executeUpdate();
            System.out.println("Type de congé saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving Type de congé: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public void listeTypeConge() {
        String sql = "SELECT * FROM type_conge";
        try {
            java.sql.PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.executeQuery();
            System.out.println("Liste des types de congé :");
            while (pstatement.getResultSet().next()) {
                int id = pstatement.getResultSet().getInt("id");
                String type = pstatement.getResultSet().getString("type");
                int nombre_jours = pstatement.getResultSet().getInt("nombre_jours");
                String description = pstatement.getResultSet().getString("description");
                System.out.println("info : " + id + "  \n" + type + "    " + nombre_jours + "  \n" + description);
            }
        } catch (Exception e) {
            System.out.println("Error listing Type de congé: " + e.getMessage());
            e.printStackTrace();
        }
    }    
    
}
