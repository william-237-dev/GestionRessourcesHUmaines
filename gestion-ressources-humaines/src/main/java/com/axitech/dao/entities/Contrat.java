package com.axitech.dao.entities;

public class Contrat {
    private int id;
    private String description;
    private boolean actif;

    private Integer employeId;
    private Integer pdgId;

    public Contrat() {
        this.actif = true;
    }

    public Contrat(int id, String description) {
        this.id = id;
        this.description = description;
        this.actif = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public Integer getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Integer employeId) {
        this.employeId = employeId;
    }

    public Integer getPdgId() {
        return pdgId;
    }

    public void setPdgId(Integer pdgId) {
        this.pdgId = pdgId;
    }
}