package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class TheseUniversitaire  extends Documents implements Empruntable, Reservable {
    private String universite;
    private String domaine;

    public String getUniversite() {
        return universite;
    }

    public String getDomaine() {
        return domaine;
    }

    public TheseUniversitaire(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, boolean isEmprunt, String universite, String domaine) {
        super(author, title, datePublication, nombreDePages, acces, isEmprunt);
        this.universite = universite;
        this.domaine = domaine;
    }

    @Override
    public String afficherDetails() {
        return "";
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

    @Override
    public void annulerReservation() {

    }
}
