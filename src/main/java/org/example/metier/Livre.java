package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class Livre extends Documents implements Empruntable, Reservable {
    private int isbn;

    public Livre(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, boolean isEmprunt, int isbn) {
        super(author, title, datePublication, nombreDePages, acces, isEmprunt);
        this.isbn = isbn;
    }

    public int getIsbn() {
        return isbn;
    }

    @Override
    public String afficherDetails() {
        return "Livre {" +
                "ID='" + id + '\'' +
                ", Titre='" + title + '\'' +
                ", Auteur='" + author + '\'' +
                ", Date de publication=" + datePublication +
                ", Nombre de pages=" + nombreDePages +
                ", ISBN=" + isbn +
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
