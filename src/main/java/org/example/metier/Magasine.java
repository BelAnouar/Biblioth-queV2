package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class Magasine  extends Documents implements Empruntable, Reservable {
    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Magasine(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces, boolean isEmprunt, int numero) {
        super(author, title, datePublication, nombreDePages, acces, isEmprunt);
        this.numero = numero;
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
