package com.axitech.dao.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.axitech.dao.entities.Contrat;
import com.axitech.dao.entities.Pdg;
import com.axitech.dao.entities.Tache;
import com.axitech.dao.entities.TypeConge;

public class PdgRepository {
    private Connection connection;
    private PersonneRepository personneRepository;

    public PdgRepository(Connection connection) {
        this.connection = connection;
        try {
            this.personneRepository = new PersonneRepository();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to initialize PersonneRepository", e);
        }
    }

    public void save(Pdg pdg) {
        int id = personneRepository.insererPersonne((com.axitech.dao.entities.Personne) pdg);
        if (id == -1) {
            System.out.println("Echec de l'insertion de la personne, PDG non cree.");
            return;
        }
        pdg.setId(id);

        String sql = "INSERT INTO pdg (id) VALUES (?)";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            System.out.println("PDG saved successfully (id=" + id + ").");
        } catch (SQLException e) {
            System.out.println("Error saving PDG: " + e.getMessage());
        }
    }

    public void listePdg() {
        String sql = "SELECT p.id, p.nom, p.prenom, p.adresse FROM personne p JOIN pdg g ON p.id = g.id";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            ResultSet rs = pstatement.executeQuery();
            System.out.println("Liste des PDG :");
            while (rs.next()) {
                System.out.println("info : " + rs.getInt("id") + "  \n" + rs.getString("nom")
                        + "    " + rs.getString("prenom") + "  \n" + rs.getString("adresse"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing PDG: " + e.getMessage());
        }
    }

    public Pdg findById(int id) {
        String sql = "SELECT p.id, p.nom, p.prenom, p.adresse FROM personne p JOIN pdg g ON p.id = g.id WHERE p.id = ?";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            ResultSet rs = pstatement.executeQuery();
            if (!rs.next()) {
                return null;
            }
            Pdg pdg = new Pdg(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"));
            pdg.setTaches(chargerTaches(id));
            pdg.setContrat(chargerContrats(id));
            pdg.setTypeconge(chargerTypeConges(id));
            return pdg;
        } catch (SQLException e) {
            System.out.println("Error loading PDG: " + e.getMessage());
            return null;
        }
    }

    // --- Composition : Tache et Contrat rattaches a ce PDG (FK pdg_id) ---

    private List<Tache> chargerTaches(int pdgId) throws SQLException {
        List<Tache> taches = new ArrayList<>();
        String sql = "SELECT * FROM tache WHERE pdg_id = ?";
        PreparedStatement pstatement = connection.prepareStatement(sql);
        pstatement.setInt(1, pdgId);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            Tache tache = new Tache(rs.getInt("id"), rs.getString("description"), rs.getDate("date"));
            tache.setPdgId(pdgId);
            taches.add(tache);
        }
        return taches;
    }

    private List<Contrat> chargerContrats(int pdgId) throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        String sql = "SELECT * FROM contrat WHERE pdg_id = ?";
        PreparedStatement pstatement = connection.prepareStatement(sql);
        pstatement.setInt(1, pdgId);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            Contrat contrat = new Contrat(rs.getInt("id"), rs.getString("description"));
            contrat.setActif(rs.getBoolean("actif"));
            contrat.setPdgId(pdgId);
            contrats.add(contrat);
        }
        return contrats;
    }

    // --- Aggregation : TypeConge (table de jointure pdg_typeconge) ---

    private List<TypeConge> chargerTypeConges(int pdgId) throws SQLException {
        List<TypeConge> typeConges = new ArrayList<>();
        String sql = "SELECT tc.* FROM typeconge tc "
                   + "JOIN pdg_typeconge ptc ON tc.id = ptc.typeconge_id "
                   + "WHERE ptc.pdg_id = ?";
        PreparedStatement pstatement = connection.prepareStatement(sql);
        pstatement.setInt(1, pdgId);
        ResultSet rs = pstatement.executeQuery();
        while (rs.next()) {
            TypeConge tc = new TypeConge(rs.getInt("id"), rs.getString("type"),
                    rs.getInt("nombre_jours"), rs.getString("description"));
            tc.setStatut(rs.getString("statut"));
            typeConges.add(tc);
        }
        return typeConges;
    }

    // --- Methodes metier : Accepter/Refuser un conge, Resilier un contrat ---

    public void accepterConge(int typeCongeId) {
        String sql = "UPDATE typeconge SET statut = 'Accepte' WHERE id = ?";
        executeUpdateStatut(sql, typeCongeId, "accepte");
    }

    public void refuserConge(int typeCongeId) {
        String sql = "UPDATE typeconge SET statut = 'Refuse' WHERE id = ?";
        executeUpdateStatut(sql, typeCongeId, "refuse");
    }

    private void executeUpdateStatut(String sql, int id, String action) {
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, id);
            pstatement.executeUpdate();
            System.out.println("Conge " + id + " " + action + " par le PDG.");
        } catch (SQLException e) {
            System.out.println("Error updating conge status: " + e.getMessage());
        }
    }

    public void resilierContrat(int contratId) {
        String sql = "UPDATE contrat SET actif = FALSE WHERE id = ?";
        try {
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setInt(1, contratId);
            pstatement.executeUpdate();
            System.out.println("Contrat " + contratId + " resilie par le PDG.");
        } catch (SQLException e) {
            System.out.println("Error resiliating contrat: " + e.getMessage());
        }
    }
}