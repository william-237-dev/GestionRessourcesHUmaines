package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Pdg extends Personne {
    private String adresse;
    private List<TypeConge> typeconge;
    private List<Tache> taches;
    private List<Contrat> contrat;


    public Pdg() {
    }


    public Pdg(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.adresse = adresse;
        this.typeconge = new ArrayList<>();
        this.taches = new ArrayList<>();
        this.contrat = new ArrayList<>();
    }

    public void ajouterTypeConge(TypeConge typeConge) {
            this.typeconge.add(typeConge);
    }

    public void ajouterTache(Tache tache) {
            this.taches.add(tache);
    }

    public void ajouterContrat(Contrat contrat) {
            this.contrat.add(contrat);
    }


    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }


    public List<TypeConge> getTypeconge() {
        return typeconge;
    }


    public List<Tache> getTaches() {
        return taches;
    }


    public List<Contrat> getContrat() {
        return contrat;
    }

    
    

    
    
}
