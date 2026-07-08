package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.axitech.dao.entities.TypeConge;

public class TypeCongeRepository {
    private Connection connection;

    public TypeCongeRepository(Connection connection) {
        this.connection = connection;
    }

    public int save(TypeConge typeConge) {
        String sql = "INSERT INTO typeconge (type, nombre_jours, description, statut) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstatement.setString(1, typeConge.getType());
            pstatement.setInt(2, typeConge.getNombre_jours());
            pstatement.setString(3, typeConge.getDescription());
            pstatement.setString(4, typeConge.getStatut());
            pstatement.executeUpdate();
            System.out.println("Type de conge saved successfully.");

            ResultSet keys = pstatement.getGeneratedKeys();
            return keys.next() ? keys.getInt(1) : -1;
        } catch (SQLException e) {
            System.out.println("Error saving type de conge: " + e.getMessage());
            return -1;
        }
    }

    public void listeTypeConge() {
        String sql = "SELECT * FROM typeconge";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des types de conge :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("type")
                        + "    " + rs.getInt("nombre_jours") + "  \n" + rs.getString("description")
                        + "  statut=" + rs.getString("statut"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing types de conge: " + e.getMessage());
        }
    }
}