package com.axitech;

import com.axitech.dao.db.Database;
import com.axitech.dao.entities.BulletinPaie;
import com.axitech.dao.entities.Comptable;
import com.axitech.dao.entities.Contrat;
import com.axitech.dao.entities.Drh;
import com.axitech.dao.entities.Employe;
import com.axitech.dao.entities.Formation;
import com.axitech.dao.entities.Mission;
import com.axitech.dao.entities.Paiement;
import com.axitech.dao.entities.Pdg;
import com.axitech.dao.entities.Personne;
import com.axitech.dao.entities.Tache;
import com.axitech.dao.entities.TypeConge;
import com.axitech.dao.repository.ComptableRepository;
import com.axitech.dao.repository.ContratRepository;
import com.axitech.dao.repository.DrhRepository;
import com.axitech.dao.repository.EmployeRepository;
import com.axitech.dao.repository.FormationRepository;
import com.axitech.dao.repository.MissionRepository;
import com.axitech.dao.repository.PaiementRepository;
import com.axitech.dao.repository.PdgRepository;
import com.axitech.dao.repository.PersonneRepository;
import com.axitech.dao.repository.TacheRepository;
import com.axitech.dao.repository.TypeCongeRepository;

import java.sql.Connection;
import java.util.Scanner;


public class App {
    private static Database database = new Database();
    private static Connection connection = database.getConnection();
    private static PersonneRepository personneRepository;
    private static EmployeRepository employeRepository ;
    private static PdgRepository pdgRepository;
    private static DrhRepository drhRepository;
    private static ComptableRepository comptableRepository;
    private static ContratRepository contratRepository;
    private static FormationRepository formationRepository;
    private static MissionRepository missionRepository;
    private static PaiementRepository paiementRepository;
    private static TacheRepository tacheRepository;
    private static TypeCongeRepository typeCongeRepository;



    public static void main(String[] args) throws Exception {


    
    PersonneRepository personneRepository = new PersonneRepository();
    EmployeRepository employeRepository = new EmployeRepository(connection);
    PdgRepository pdgRepository = new PdgRepository(connection);
    DrhRepository drhRepository = new DrhRepository(connection);
    ComptableRepository comptableRepository = new ComptableRepository(connection);
    ContratRepository contratRepository = new ContratRepository(connection);
    FormationRepository formationRepository = new FormationRepository(connection);
    MissionRepository missionRepository = new MissionRepository(connection);
    PaiementRepository paiementRepository = new PaiementRepository(connection);
    TacheRepository tacheRepository = new TacheRepository(connection);
    TypeCongeRepository typeCongeRepository = new TypeCongeRepository(connection);



        Personne personne = new Personne();
        Employe employe = new Employe();
        Pdg pdg = new Pdg(); 
        Drh drh = new Drh();
        Comptable comptable = new Comptable();
        BulletinPaie bulletinPaie = new BulletinPaie();
        Contrat contrat = new Contrat();
        Formation formation = new Formation();
        Mission mission = new Mission();
        Paiement paiement = new Paiement();
        Tache tache = new Tache();
        TypeConge typeConge = new TypeConge();



      // CODE POUR INSERER UNE PERSONNE et afficher la liste des PERSONNES

        Scanner scanner = new Scanner(System.in);

        System.out.print("Entre votre nom: ");
        String nom = scanner.nextLine();

        System.out.print("Entre votre prenom: ");
        String prenom = scanner.nextLine();

        System.out.print("Entre votre adresse: ");
        String adresse = scanner.nextLine();

        personne.setNom(nom);
        personne.setPrenom(prenom);
        personne.setAdresse(adresse);
        
        personneRepository.save(personne);
    
        



        System.out.println("Liste des employes :");
        employeRepository.listemployer();  

        

  /**    
    
        // CODE POUR INSERER UN EMPLOYE et afficher la liste des EMPLOYES
        
      
        Scanner scanner0 = new Scanner(System.in);

        System.out.print("Entre votre nom: ");
        String nom0 = scanner0.nextLine();

        System.out.print("Entre votre prenom: ");
        String prenom0 = scanner0.nextLine();

        System.out.print("Entre votre adresse: ");
        String adresse0 = scanner0.nextLine();

        employe.setNom(nom0);
        employe.setPrenom(prenom0);
        employe.setAdresse(adresse0);

        employeRepository.save(employe);


        System.out.println("Liste des employes :");
        employeRepository.listemployer();     
          
        */

/** 


        // CODE POUR INSERER UN PDG et afficher la liste des PDG


        Scanner scanner1 = new Scanner(System.in);

        System.out.print("Entre votre nom: ");
        String nom1 = scanner1.nextLine();

        System.out.print("Entre votre prenom: ");
        String prenom1 = scanner1.nextLine(); 

        System.out.print("Entre votre adresse: ");
        String adresse1 = scanner1.nextLine();

        pdg.setNom(nom1);
        pdg.setPrenom(prenom1);
        pdg.setAdresse(adresse1);

        pdgRepository.save(pdg);


        System.out.println("Liste des PDG :");
        pdgRepository.listePdg();

        */

        
     /** 
    
        // CODE POUR INSERER UN DRH et afficher la liste des DRH



        Scanner scanner2 = new Scanner(System.in);
        System.out.print("Entre votre nom: ");
        String nom2 = scanner2.nextLine();

        System.out.print("Entre votre prenom: ");
        String prenom2 = scanner2.nextLine();

        System.out.print("Entre votre adresse: ");
        String adresse2 = scanner2.nextLine();

        drh.setNom(nom2);
        drh.setPrenom(prenom2);
        drh.setAdresse(adresse2);

        drhRepository.save(drh);


        System.out.println("Liste des DRH :");
        drhRepository.listeDrh();

      
     
        //CODE POUR INSERER UN COMPTABLE et afficher la liste des COMPTABLES

        Scanner scanner3 = new Scanner(System.in);
        System.out.print("Entre votre nom: ");
        String nom3 = scanner3.nextLine();

        System.out.print("Entre votre prenom: ");
        String prenom3 = scanner3.nextLine();

        System.out.print("Entre votre adresse: ");
        String adresse3 = scanner3.nextLine();

        comptable.setNom(nom3);
        comptable.setPrenom(prenom3);
        comptable.setAdresse(adresse3);

        comptableRepository.save(comptable);



        System.out.println("Liste des Comptables :");
        comptableRepository.listeComptable();




        // CODE POUR INSERER UN BULLETIN DE PAIE et afficher la liste des BULLETINS DE PAIE


        Scanner scanner4 = new Scanner(System.in);
        System.out.print("Entre le nom de l'employe: ");
        String nom4 = scanner4.nextLine();

        bulletinPaie.setNom_employe(nom4);  

        bulletinPaieRepository.save(bulletinPaie);

        System.out.println("Liste des bulletins de paie :");
        bulletinPaieRepository.listeBulletinPaie();


 
         // CODE POUR INSERER UN CONTRAT et afficher la liste des CONTRATS

        Scanner scanner5 = new Scanner(System.in);
        System.out.print("Entre la description du contrat: ");
        String description = scanner5.nextLine();

        contrat.setDescription(description);
        contratRepository.save(contrat);

        System.out.println("Liste des contrats :");
        contratRepository.listeContrat();



        //CODE POUR INSERER UNE FORMATION et afficher la liste des FORMATIONS

        Scanner scanner6 = new Scanner(System.in);
        System.out.print("Entre la description de la formation: ");
        String description2 = scanner6.nextLine();

        formation.setDescription(description2);
        formationRepository.save(formation);

        System.out.println("Liste des formations :");
        formationRepository.listeFormation();



        // CODE POUR  INSERER UNE MISSION et afficher la liste des MISSIONS

        Scanner scanner7 = new Scanner(System.in);
        System.out.print("Entre la description de la mission: ");
        String description3 = scanner7.nextLine();

        System.out.print("Entre la date de la mission (yyyy-mm-dd): ");
        String dateMission = scanner7.nextLine();

        mission.setDescription(description3);
        mission.setDate(Date.valueOf(dateMission)); // Convert string to Date
        missionRepository.save(mission);

        System.out.println("Liste des missions :");
        missionRepository.listeMission();



        // CODE POUR INSERER UN PAIEMENT et afficher la liste des PAIEMENTS

        Scanner scanner8 = new Scanner(System.in);
        System.out.print("Entre le montant du paiement: ");
        double montant = scanner8.nextDouble();

        System.out.print("Entre la date du paiement: ");
        String datePaiement = scanner8.nextLine();

        paiement.setMontant(montant);
        paiement.setDatePaiement(datePaiement);
        paiementRepository.save(paiement);

        System.out.println("Liste des paiements :");
        paiementRepository.listePaiement();


        // CODE POUR INSERER UNE TACHE et afficher la liste des TACHES

        Scanner scanner9 = new Scanner(System.in);
        System.out.print("Entre la description de la tache: ");
        String description4 = scanner9.nextLine();

        System.out.print("Entre la date de la tache: ");
        String dateTache = scanner9.nextLine();

        tache.setDescription(description4);
        tache.setDate(Date.valueOf(dateTache)); // Convert string to Date
        tacheRepository.save(tache);

        System.out.println("Liste des taches :");
        tacheRepository.listeTache();

        
        

        // CODE POUR INSERER UN TYPE DE CONGE et afficher la liste des TYPES DE CONGE

        Scanner scanner10 = new Scanner(System.in);
        System.out.print("Entre la description du type de conge: ");
        String description5 = scanner10.nextLine();

        System.out.print("Entre le nombre de jours du type de conge: ");
        int nombreJours = scanner10.nextInt();

        System.out.print(" Entrer le type de conge ");
        String type = scanner10.nextLine();

        typeConge.setDescription(description5);
        typeCongeRepository.save(typeConge);

        System.out.println("Liste des types de conge :");
        typeCongeRepository.listeTypeConge();

        */
    }    

}  