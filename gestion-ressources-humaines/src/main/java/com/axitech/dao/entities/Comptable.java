package com.axitech.dao.entities;

import java.util.ArrayList;
import java.util.List;

public class Comptable extends Personne {

    private List<BulletinPaie> bulletinsPaie;

    public Comptable() {
    }

    public Comptable(int id, String nom, String prenom, String adresse) {
        super(id, nom, prenom, adresse);
        this.bulletinsPaie = new ArrayList<>();
    }

    public void ajouterBulletinPaie(BulletinPaie bulletinPaie) {
        this.bulletinsPaie.add(bulletinPaie);
    }

    // --- Methode metier du diagramme de cas d'utilisation ---

    public BulletinPaie editerBulletinPaie(Employe employe) {
        List<Employe> employesConcernes = new ArrayList<>();
        employesConcernes.add(employe);

        BulletinPaie bulletin = new BulletinPaie(0, employesConcernes);
        this.bulletinsPaie.add(bulletin);
        System.out.println("Bulletin de paie edite pour " + employe.getPrenom() + " " + employe.getNom());
        return bulletin;
    }

    public List<BulletinPaie> getBulletinsPaie() {
        return bulletinsPaie;
    }

    public void setBulletinsPaie(List<BulletinPaie> bulletinsPaie) {
        this.bulletinsPaie = bulletinsPaie;
    }
}