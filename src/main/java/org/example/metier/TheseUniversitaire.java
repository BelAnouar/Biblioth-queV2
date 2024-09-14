package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class TheseUniversitaire extends Documents implements Empruntable, Reservable {
    private String universite;
    private String domaine;

    public String getUniversite() {
        return universite;
    }

    public String getDomaine() {
        return domaine;
    }

    public TheseUniversitaire() {
        super();
    }

    public TheseUniversitaire(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  String universite, String domaine) {
        super(author, title, datePublication, nombreDePages, acces);
        this.universite = universite;
        this.domaine = domaine;
    }
    public TheseUniversitaire(int id,String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  String universite, String domaine) {
        super(id,author, title, datePublication, nombreDePages, acces);
        this.universite = universite;
        this.domaine = domaine;
    }

    @Override
    public String afficherDetails() {
        return "TheseUniversitaire{" +
                "universite='" + universite + '\'' +
                ", domaine='" + domaine + '\'' +
                ", isEmprunt=" + isEmprunt +
                ", acces=" + acces +
                ", nombreDePages=" + nombreDePages +
                ", datePublication=" + datePublication +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }


    @Override
    public void emprunter() {

    }

    @Override
    public void retourner() {

    }

    @Override
    public void reserver() {

    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public void annulerReservation() {

    }
}
