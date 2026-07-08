package com.axitech.dao.entities;

import java.util.List;

public class Paiement {
    private int id;
    private List<BulletinPaie> bulletinPaie;
    private double montant;
    private String datePaiement;
    private List<Employe> employes;
    private Integer drhId;

    public Paiement() {
    }

    public Paiement(int id, List<BulletinPaie> bulletinPaie, double montant, String datePaiement, List<Employe> employes) {
        this.id = id;
        this.bulletinPaie = bulletinPaie;
        this.montant = montant;
        this.datePaiement = datePaiement;
        this.employes = employes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BulletinPaie> getBulletinPaie() {
        return bulletinPaie;
    }

    public void setBulletinPaie(List<BulletinPaie> bulletinPaie) {
        this.bulletinPaie = bulletinPaie;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public String getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public Integer getDrhId() {
        return drhId;
    }

    public void setDrhId(Integer drhId) {
        this.drhId = drhId;
    }
}