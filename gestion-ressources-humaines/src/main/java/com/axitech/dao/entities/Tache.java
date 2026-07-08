package com.axitech.dao.entities;

import java.sql.Date;

public class Tache {
    private int id;
    private String description;
    private Date date;

    // Cote "proprietaire" de la composition Employe/Pdg -> Tache.
    // L'un des deux est non-nul, jamais les deux.
    private Integer employeId;
    private Integer pdgId;

    public Tache() {
    }

    public Tache(int id, String description, Date date) {
        this.id = id;
        this.description = description;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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