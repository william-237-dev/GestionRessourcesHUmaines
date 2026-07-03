package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Drh extends Personne {
    private String adresse;
    private List<Mission> missions;
    private List<Paiement> paiements;

    public Drh() {
    }

    public Drh(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.adresse = adresse;
        this.missions = new ArrayList<>();
        this.paiements = new ArrayList<>();
    }

    public void ajouterMission(Mission mission) {
            this.missions.add(mission);
    }

    public void ajouterPaiement(Paiement paiement) {
            this.paiements.add(paiement);
    }

    public String getAdresse() {
        return adresse;
    }


    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    

       
    
}
