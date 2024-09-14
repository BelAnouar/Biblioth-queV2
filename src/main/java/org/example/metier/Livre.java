package org.example.metier;

import org.example.Enum.UtilisateurType;
import org.example.metier.abstracts.Documents;
import org.example.metier.interfaces.Empruntable;
import org.example.metier.interfaces.Reservable;

import java.time.LocalDate;

public class Livre extends Documents implements Empruntable, Reservable {
    private int isbn;
    public  Livre(){
        super();
    }
    public Livre(String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  int isbn) {
        super(author, title, datePublication, nombreDePages, acces);
        this.isbn = isbn;
    }

    public Livre(int id,String author, String title, LocalDate datePublication, int nombreDePages, UtilisateurType acces,  int isbn) {
        super(id,author, title, datePublication, nombreDePages, acces);
        this.isbn = isbn;
    }



    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getIsbn() {
        return isbn;
    }

    @Override
    public String afficherDetails() {
        return "Livre{" +
                "isbn=" + isbn +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", datePublication=" + datePublication +
                ", nombreDePages=" + nombreDePages +
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
