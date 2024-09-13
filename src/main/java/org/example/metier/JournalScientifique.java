package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class JournalScientifique  extends Documents implements Empruntable, Reservable {
    private String domaineRecherche;

    public JournalScientifique(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, boolean isEmprunt,  String domaineRecherche) {
        super(author, title, datePublication, nombreDePages, acces, isEmprunt);
        this.domaineRecherche = domaineRecherche;
    }

    public String getDomaineRecherche() {
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
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
