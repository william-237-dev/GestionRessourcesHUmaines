package com.axitech.service;

import java.util.List;

import com.axitech.dao.db.Database;
import com.axitech.dao.entities.Personne;
import com.axitech.dao.repository.PersonneRepository;

public class Personneservice {
    private Database database;
    private PersonneRepository personneRepository;

    public Personneservice() throws ClassNotFoundException {
        this.database = new Database();
        this.personneRepository = new PersonneRepository();
    }

    

    public Personneservice(Database database, PersonneRepository personneRepository) {
        this.database = database;
        this.personneRepository = personneRepository;
    }

    public List<Personne> getAllPersonne(){
        return this.personneRepository.findAll();
    }


    public String ajouterPersonne(Personne personne) {
        String nom = personne.getNom();
        String prenom = personne.getPrenom();
        String adresse = personne.getAdresse();

        if (nom == null || prenom == null || adresse == null) {
            return "remplir les informations";
        } else{
            personneRepository.save(personne);
            return "PERSONNE AJOUTEE AVEC SUCCES";
        }
    }
public List<Personne> listerPersonnes() throws ClassNotFoundException {
    if (personneRepository == null) {
        personneRepository = new PersonneRepository();
    }
    return personneRepository.findAll();
}
}