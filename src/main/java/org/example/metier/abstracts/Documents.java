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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public void setNombreDePages(int nombreDePages) {
        this.nombreDePages = nombreDePages;
    }

    public void setAcces(UtilisateurType acces) {
        this.acces = acces;
    }

    public void setEmprunt(boolean emprunt) {
        isEmprunt = emprunt;
    }

    protected  Documents(){}

    public void setId(int id) {
        this.id = id;
    }

    protected Documents(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces) {
        this.author = author;
        this.title = title;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
        this.acces = acces;


    }
    protected Documents(int id,String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces) {
        this.id=id;
        this.author = author;
        this.title = title;
        this.datePublication = datePublication;
        this.nombreDePages = nombreDePages;
        this.acces = acces;


    }
    public int getId() {
        return id;
    }

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







    public abstract String afficherDetails();
}
