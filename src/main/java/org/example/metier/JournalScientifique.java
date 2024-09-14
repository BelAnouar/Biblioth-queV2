package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class JournalScientifique extends Documents implements Empruntable, Reservable {
    private String domaineRecherche;

    public JournalScientifique() {
        super();
    }

    public JournalScientifique(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, String domaineRecherche) {
        super(author, title, datePublication, nombreDePages, acces);
        this.domaineRecherche = domaineRecherche;
    }
    public JournalScientifique(int id,String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, String domaineRecherche) {
        super(id,author, title, datePublication, nombreDePages, acces);
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
        return "JournalScientifique{" +
                "domaineRecherche='" + domaineRecherche + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", nombreDePages=" + nombreDePages +
                ", datePublication=" + datePublication +
                ", acces=" + acces +
                ", isEmprunt=" + isEmprunt +
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

    @Override
    public void annulerReservation() {

    }
}
