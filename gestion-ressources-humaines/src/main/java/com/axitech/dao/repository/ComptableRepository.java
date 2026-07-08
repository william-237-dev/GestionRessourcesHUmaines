package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.axitech.dao.entities.Comptable;

public class ComptableRepository {
    private Connection connection;
    private PersonneRepository personneRepository;

    public ComptableRepository(Connection connection) {
        this.connection = connection;
        this.personneRepository = new PersonneRepository(connection);
    }

    public void save(Comptable comptable) {
        int id = personneRepository.insererPersonne(comptable, "COMPTABLE");
        if (id == -1) {
            System.out.println("Echec de l'insertion de la personne, Comptable non cree.");
            return;
        }
        comptable.setId(id);

        String sql = "INSERT INTO comptable (id) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            System.out.println("Comptable saved successfully (id=" + id + ").");
        } catch (SQLException e) {
            System.out.println("Error saving Comptable: " + e.getMessage());
        }
    }

    public void listeComptable() {
        String sql = "SELECT p.id, p.nom, p.prenom, p.adresse FROM personne p JOIN comptable c ON p.id = c.id";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des Comptables :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("nom")
                        + "    " + rs.getString("prenom") + "  \n" + rs.getString("adresse"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing Comptables: " + e.getMessage());
        }
    }

    /** Composition : edite un nouveau BulletinPaie rattache a ce Comptable, pour un Employe donne. */
    public int editerBulletinPaie(int comptableId, int employeId) {
        String sql = "INSERT INTO bulletinpaie (comptable_id) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstatement.setInt(1, comptableId);
            pstatement.executeUpdate();

            ResultSet keys = pstatement.getGeneratedKeys();
            if (keys.next()) {
                int bulletinId = keys.getInt(1);
                String sqlLien = "INSERT INTO bulletinpaie_employe (bulletinpaie_id, employe_id) VALUES (?, ?)";
                PreparedStatement pstatementLien = connection.prepareStatement(sqlLien);
                pstatementLien.setInt(1, bulletinId);
                pstatementLien.setInt(2, employeId);
                pstatementLien.executeUpdate();
                System.out.println("Bulletin de paie " + bulletinId + " edite pour l'employe " + employeId);
                return bulletinId;
            }
            return -1;
        } catch (SQLException e) {
            System.out.println("Error editing bulletin de paie: " + e.getMessage());
            return -1;
        }
    }
}
