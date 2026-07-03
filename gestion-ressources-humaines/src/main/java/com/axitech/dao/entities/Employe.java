package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Employe extends Personne {
    
    private String adresse;
    private List<TypeConge> typeconge;
    private List<Tache> taches;
    private List<Contrat> contrat;
    private List<Formation> formations;
    
    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.adresse = adresse;
        this.typeconge = new ArrayList<>();
        this.taches = new ArrayList<>(); 
        this.contrat = new ArrayList<>();
        this.contrat.add(new Contrat(1,"contrat long terme")); 

        this.formations = new ArrayList<>();
    }
    
    
    public void ajouterTypeConge(TypeConge typeConge) {
            this.typeconge.add(typeConge);
    }

    public void ajouterTache(Tache tache) {
            this.taches.add(tache);
    }

    public void ajouterFormation(Formation formation) {
            this.formations.add(formation);
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

    public List<Formation> getFormations() {
        return formations;
    }

    
}
