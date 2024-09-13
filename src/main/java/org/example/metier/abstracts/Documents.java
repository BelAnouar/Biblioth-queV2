package org.example.metier.abstracts;

import org.example.Enum.UtilisateurType;

import java.time.LocalDate;

public abstract  class Documents {
    protected int id;
    protected String title;
    protected String author;
    protected LocalDate datePublication;
    protected int nombreDePages;
    protected UtilisateurType acces;
    protected boolean isEmprunt;


    public boolean isEmprunt() {
        return isEmprunt;
    }

    public UtilisateurType getAcces() {
        return acces;
    }

    public int getNombreDePages() {
        return nombreDePages;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }





    protected Documents(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, boolean isEmprunt) {
        this.author = author;
        this.title = title;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
        this.acces = acces;
        this.isEmprunt = isEmprunt;

    }

    public abstract String afficherDetails();
}
