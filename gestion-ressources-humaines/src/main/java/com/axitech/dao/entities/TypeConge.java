package com.axitech.dao.entities;

public class TypeConge {
    private int id;
    private String type;
    private int nombre_jours;
    private String description;
    private String statut; // "En attente", "Accepte", "Refuse"

    public TypeConge() {
        this.statut = "En attente";
    }

    public TypeConge(int id, String type, int nombre_jours, String description) {
        this.id = id;
        this.type = type;
        this.nombre_jours = nombre_jours;
        this.description = description;
        this.statut = "En attente";
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

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}