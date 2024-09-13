package org.example.metier.abstracts;

public abstract class Utilisateur {
    protected int id ;
    protected String nom;
    protected String prenom ;


    protected Utilisateur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
}
