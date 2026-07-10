package com.axitech.controller;

import java.io.IOException;
import java.util.List;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import com.axitech.dao.entities.Personne;
import com.axitech.service.Personneservice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/personne")
public class Personnecontroller extends HttpServlet {
    private Personneservice personneservice;






    public Personnecontroller() {
        try {
            this.personneservice = new Personneservice();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unable to initialize Personneservice", e);
        }
    }


    // Removed invalid constructor with wrong name
    public Personnecontroller(Personneservice personneservice) {
        this.personneservice = personneservice;
    }




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Formpersonne.html").forward(req, resp);

              List<Personne> personnes = null;
        try {
            personnes = personneservice.listerPersonnes();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.setAttribute("personnes", personnes);
        req.getRequestDispatcher("Listpersonne.html").forward(req, resp);
    }
    



    
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {

    String nom = req.getParameter("nom");
    String prenom = req.getParameter("prenom");
    String adresse = req.getParameter("adresse");

    Personne personne = new Personne();
    personne.setNom(nom);
    personne.setPrenom(prenom);
    personne.setAdresse(adresse);

    personneservice.ajouterPersonne(personne);

    List<Personne> personnes = personneservice.getAllPersonne();

    TemplateEngine engine =
            ThymeleafConfig.getTemplateEngine(getServletContext());

    JakartaServletWebApplication application =
            JakartaServletWebApplication.buildApplication(getServletContext());

    WebContext context =
            new WebContext(application.buildExchange(req, resp));

    context.setVariable("listpersonne", personnes);

    resp.setContentType("text/html;charset=UTF-8");

    engine.process("listpersonne", context, resp.getWriter());
}


}

