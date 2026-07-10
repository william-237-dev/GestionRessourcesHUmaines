package com.axitech.controller;

import java.io.IOException;
import java.util.List;

import com.axitech.dao.entities.Personne;
import com.axitech.dao.repository.PersonneRepository;
import com.axitech.service.Personneservice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/List")
public class ListePersonnesServlet extends HttpServlet {

    private Personneservice personneservice;

    @Override
    public void init() throws ServletException {
        try {
            personneservice = new Personneservice();
        } catch (ClassNotFoundException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Personne> personnes = null;
        try {
            personnes = personneservice.listerPersonnes();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        req.setAttribute("personnes", personnes);
        req.getRequestDispatcher("/Listpersonne.html").forward(req, resp);
    }
}