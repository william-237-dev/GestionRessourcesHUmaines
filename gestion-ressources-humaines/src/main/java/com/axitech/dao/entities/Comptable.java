package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Comptable extends Personne {
    private String adresse;
    private List<BulletinPaie> BulletinPaie;
    
    public Comptable() {
    }

    public Comptable(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.adresse = adresse;
        this.BulletinPaie = new ArrayList<>();
    }

    public void ajouterBulletinPaie(BulletinPaie bulletinPaie) {
            this.BulletinPaie.add(bulletinPaie);
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<BulletinPaie> getBulletinPaie() {
        return BulletinPaie;
    }
    
}
