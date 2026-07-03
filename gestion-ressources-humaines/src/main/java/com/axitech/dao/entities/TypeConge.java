package com.axitech.dao.entities;

import java.util.List;

public class TypeConge {
    private int id;
    private String type;
    private int nombre_jours;
    private String description;
    private List<Employe> employes;
   
    public TypeConge() {
    }

    public TypeConge(int id, String type, int nombre_jours, String description, List<Employe> employes) {
        this.id = id;
        this.type = type;
        this.nombre_jours = nombre_jours;
        this.description = description;
        this.employes = employes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNombre_jours() {
        return nombre_jours;
    }

    public void setNombre_jours(int nombre_jours) {
        this.nombre_jours = nombre_jours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    
}
