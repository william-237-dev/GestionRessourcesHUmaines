package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Drh extends Personne {

    private List<Mission> missions;
    private List<Paiement> paiements;
    private List<Employe> employes;

    public Drh() {
    }

    public Drh(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.missions = new ArrayList<>();
        this.paiements = new ArrayList<>();
        this.employes = new ArrayList<>();
    }

    public void ajouterMission(Mission mission) {
        this.missions.add(mission);
    }

    public void ajouterPaiement(Paiement paiement) {
        this.paiements.add(paiement);
    }

    // --- Methodes metier du diagramme de cas d'utilisation ---

    public void recruterPersonnel(Employe employe) {
        this.employes.add(employe);
        System.out.println("Personnel recrute : " + employe.getPrenom() + " " + employe.getNom());
    }

    public boolean verifierAssiduite(Employe employe, List<Tache> tachesEffectuees) {
        boolean assidu = tachesEffectuees != null && !tachesEffectuees.isEmpty();
        System.out.println("Assiduite de " + employe.getPrenom() + " : " + (assidu ? "OK" : "Insuffisante"));
        return assidu;
    }

    public void attribuerTache(Employe employe, Tache tache) {
        employe.ajouterTache(tache);
        System.out.println("Tache attribuee a " + employe.getPrenom() + " : " + tache.getDescription());
    }

    public void payerEmploye(Employe employe, Paiement paiement) {
        this.paiements.add(paiement);
        System.out.println("Paiement de " + paiement.getMontant() + " effectue pour " + employe.getPrenom());
    }

    public void accorderConge(Employe employe, TypeConge conge) {
        conge.setStatut("Accepte");
        employe.ajouterTypeConge(conge);
        System.out.println("Conge accorde par le DRH a " + employe.getPrenom());
    }

    public void accorderPromotion(Employe employe, String nouveauPoste) {
        System.out.println(employe.getPrenom() + " " + employe.getNom() + " est promu(e) : " + nouveauPoste);
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }
}