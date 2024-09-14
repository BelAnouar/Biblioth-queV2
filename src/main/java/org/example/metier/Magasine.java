package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class Magasine  extends Documents implements Empruntable, Reservable {
    private int numero;


    public Magasine(){
        super();
    }
    public Magasine(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  int numero) {
        super(author, title, datePublication, nombreDePages, acces);
        this.numero = numero;
    }

    public Magasine( int id,String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  int numero) {
        super(id,author, title, datePublication, nombreDePages, acces);
        this.numero = numero;
    }
    @Override
    public String afficherDetails() {
        return "Magasine{" +
                "numero=" + numero +
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
