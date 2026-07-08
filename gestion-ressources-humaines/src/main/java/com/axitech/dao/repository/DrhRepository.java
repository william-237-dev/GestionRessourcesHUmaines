package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.axitech.dao.entities.Drh;
import com.axitech.dao.entities.Mission;
import com.axitech.dao.entities.Paiement;

public class DrhRepository {
    private Connection connection;
    private PersonneRepository personneRepository;

    public DrhRepository(Connection connection) {
        this.connection = connection;
        this.personneRepository = new PersonneRepository(connection);
    }

    public void save(Drh drh) {
        // insererPersonne expects a Personne; Drh extends Personne so cast explicitly
        int id = personneRepository.setPersonne((com.axitech.dao.entities.Personne) drh);
        if (id == -1) {
            System.out.println("Echec de l'insertion de la personne, DRH non cree.");
            return;
        }
        drh.setId(id);

        String sql = "INSERT INTO drh (id) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            System.out.println("DRH saved successfully (id=" + id + ").");
        } catch (SQLException e) {
            System.out.println("Error saving DRH: " + e.getMessage());
        }
    }

    public void listeDrh() {
        String sql = "SELECT p.id, p.nom, p.prenom, p.adresse FROM Personne p JOIN drh d ON p.id = d.id";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des DRH :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("nom")
                        + "    " + rs.getString("prenom") + "  \n" + rs.getString("adresse"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing DRH: " + e.getMessage());
        }
    }

    public Drh findById(int id) {
        String sql = "SELECT p.id, p.nom, p.prenom, p.adresse FROM personne p JOIN drh d ON p.id = d.id WHERE p.id = ?";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            ResultSet rs = pstatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Drh drh = new Drh(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"));
            drh.setMissions(chargerMissions(id));
            drh.setPaiements(chargerPaiements(id));
            return drh;
        } catch (SQLException e) {
            System.out.println("Error loading DRH: " + e.getMessage());
            return null;
        }
    }

    // --- Composition : Mission et Paiement rattaches a ce DRH (FK drh_id) ---

    private List<Mission> chargerMissions(int drhId) throws SQLException {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT * FROM mission WHERE drh_id = ?";
        PreparedStatement pstatement = connection.prepareStatement(sql);
        pstatement.setInt(1, drhId);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            Mission mission = new Mission(rs.getInt("id"), rs.getString("description"), rs.getDate("date"));
            mission.setDrhId(drhId);
            missions.add(mission);
        }
        return missions;
    }

    private List<Paiement> chargerPaiements(int drhId) throws SQLException {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE drh_id = ?";
        PreparedStatement pstatement = connection.prepareStatement(sql);
        pstatement.setInt(1, drhId);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            Paiement paiement = new Paiement();
            paiement.setId(rs.getInt("id"));
            paiement.setMontant(rs.getDouble("montant"));
            paiement.setDatePaiement(rs.getString("datePaiement"));
            paiement.setDrhId(drhId);
            paiements.add(paiement);
        }
        return paiements;
    }

    // --- Methodes metier du diagramme de cas d'utilisation ---

    /** Aggregation : associe un Employe deja existant a ce DRH (table drh_employe). */
    public void recruterPersonnel(int drhId, int employeId) {
        String sql = "INSERT INTO drh_employe (drh_id, employe_id) VALUES (?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, drhId);
            pstatement.setInt(2, employeId);
            pstatement.executeUpdate();
            System.out.println("Employe " + employeId + " recrute par le DRH " + drhId);
        } catch (SQLException e) {
            System.out.println("Error recruiting: " + e.getMessage());
        }
    }

    /** Composition : cree une nouvelle Mission rattachee a ce DRH. */
    public void ajouterMission(int drhId, Mission mission) {
        String sql = "INSERT INTO mission (description, date, drh_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, mission.getDescription());
            // mission.getDate() may be of different runtime types (String or Date).
            Object dateObj = mission.getDate();
            if (dateObj == null) {
                pstatement.setNull(2, java.sql.Types.DATE);
            } else if (dateObj instanceof java.sql.Date) {
                pstatement.setDate(2, (java.sql.Date) dateObj);
            } else if (dateObj instanceof Date) {
                pstatement.setDate(2, new java.sql.Date(((Date) dateObj).getTime()));
            } else {
                // assume String representation
                try {
                    String ds = dateObj.toString();
                    java.sql.Date sqlDate;
                    try {
                        sqlDate = java.sql.Date.valueOf(ds);
                    } catch (IllegalArgumentException ex) {
                        Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(ds);
                        sqlDate = new java.sql.Date(utilDate.getTime());
                    }
                    pstatement.setDate(2, sqlDate);
                } catch (ParseException pe) {
                    pstatement.setNull(2, java.sql.Types.DATE);
                }
            }
            pstatement.setInt(3, drhId);
            pstatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error adding mission: " + e.getMessage());
        }
    }

    /** Composition : effectue un Paiement rattache a ce DRH, et le lie a l'Employe concerne. */
    public void payerEmploye(int drhId, int employeId, double montant, String datePaiement) {
        String sqlPaiement = "INSERT INTO paiement (montant, datePaiement, drh_id) VALUES (?, ?, ?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sqlPaiement, Statement.RETURN_GENERATED_KEYS);
            pstatement.setDouble(1, montant);
            pstatement.setString(2, datePaiement);
            pstatement.setInt(3, drhId);
            pstatement.executeUpdate();

            ResultSet keys = pstatement.getGeneratedKeys();
            if (keys.next()) {
                int paiementId = keys.getInt(1);
                String sqlLien = "INSERT INTO paiement_employe (paiement_id, employe_id) VALUES (?, ?)";
                PreparedStatement pstatementLien = connection.prepareStatement(sqlLien);
                pstatementLien.setInt(1, paiementId);
                pstatementLien.setInt(2, employeId);
                pstatementLien.executeUpdate();
                System.out.println("Paiement de " + montant + " effectue pour l'employe " + employeId);
            }
        } catch (SQLException e) {
            System.out.println("Error paying employe: " + e.getMessage());
        }
    }

    /** Accorde un conge (aggregation) : lie un TypeConge existant a l'employe et l'accepte directement. */
    public void accorderConge(int employeId, int typeCongeId) {
        try {
            String sqlLien = "INSERT INTO employe_typeconge (employe_id, typeconge_id) VALUES (?, ?)";
            PreparedStatement pstatementLien = connection.prepareStatement(sqlLien);
            pstatementLien.setInt(1, employeId);
            pstatementLien.setInt(2, typeCongeId);
            pstatementLien.executeUpdate();

            String sqlStatut = "UPDATE typeconge SET statut = 'Accepte' WHERE id = ?";
            PreparedStatement pstatementStatut = connection.prepareStatement(sqlStatut);
            pstatementStatut.setInt(1, typeCongeId);
            pstatementStatut.executeUpdate();

            System.out.println("Conge accorde par le DRH a l'employe " + employeId);
        } catch (SQLException e) {
            System.out.println("Error according conge: " + e.getMessage());
        }
    }

    public void accorderPromotion(int employeId, String nouveauPoste) {
        // Pas de colonne dediee dans le schema actuel : log uniquement.
        System.out.println("Employe " + employeId + " promu(e) : " + nouveauPoste);
    }
}
