package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Pdg extends Personne {

    private List<TypeConge> typeconge;
    private List<Tache> taches;
    private List<Contrat> contrat;

    public Pdg() {
    }

    public Pdg(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
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

    // --- Methodes metier du diagramme de cas d'utilisation ---

    public void accepterConge(TypeConge conge) {
        conge.setStatut("Accepte");
        System.out.println("Conge accepte par le PDG : " + conge.getType());
    }

    public void refuserConge(TypeConge conge) {
        conge.setStatut("Refuse");
        System.out.println("Conge refuse par le PDG : " + conge.getType());
    }

    public void resilierContrat(Contrat contrat) {
        contrat.setActif(false);
        System.out.println("Contrat resilie par le PDG : " + contrat.getDescription());
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
}