package com.axitech.dao.entities;

import java.util.List;

public class BulletinPaie {
    private int id;
    private List<Employe> employes;
    private Integer comptableId;

    public BulletinPaie() {
    }

    public BulletinPaie(int id, List<Employe> employes) {
        this.id = id;
        this.employes = employes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public Integer getComptableId() {
        return comptableId;
    }

    public void setComptableId(Integer comptableId) {
        this.comptableId = comptableId;
    }
}