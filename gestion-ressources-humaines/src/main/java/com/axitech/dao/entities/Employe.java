package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Employe extends Personne {

    private List<TypeConge> typeconge;
    private List<Tache> taches;
    private List<Contrat> contrat;
    private List<Formation> formations;
    private boolean demissionnaire;

    public Employe() {
    }

    public Employe(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.typeconge = new ArrayList<>();
        this.taches = new ArrayList<>();
        this.contrat = new ArrayList<>();
        this.formations = new ArrayList<>();
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

    public void ajouterFormation(Formation formation) {
        this.formations.add(formation);
    }

    // --- Methodes metier du diagramme de cas d'utilisation ---

    public void demanderConge(TypeConge conge) {
        conge.setStatut("En attente");
        this.typeconge.add(conge);
        System.out.println(this.getPrenom() + " " + this.getNom()
                + " a demande un conge (" + conge.getType() + ")");
    }

    public List<Formation> consulterFormations() {
        return this.formations;
    }

    public void demanderDemission() {
        this.demissionnaire = true;
        System.out.println(this.getPrenom() + " " + this.getNom() + " a demande sa demission.");
    }

    public boolean isDemissionnaire() {
        return demissionnaire;
    }

    public void setDemissionnaire(boolean demissionnaire) {
        this.demissionnaire = demissionnaire;
    }

    public List<TypeConge> getTypeconge() {
        return typeconge;
    }

    public void setTypeconge(List<TypeConge> typeconge) {
        this.typeconge = typeconge;
    }

    public List<Tache> getTaches() {
        return taches;
    }

    public void setTaches(List<Tache> taches) {
        this.taches = taches;
    }

    public List<Contrat> getContrat() {
        return contrat;
    }

    public void setContrat(List<Contrat> contrat) {
        this.contrat = contrat;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }
}