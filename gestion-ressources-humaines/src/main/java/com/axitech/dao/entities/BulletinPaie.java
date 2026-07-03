package com.axitech.dao.entities;

import java.util.List;

public class BulletinPaie {
    private int id;
    private List<Employe> employes;
    
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

    public String getNom_employe() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNom_employe'");
    }

    public void setNom_employe(String nom4) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNom_employe'");
    }

    

}
