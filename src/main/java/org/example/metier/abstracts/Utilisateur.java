package org.example.metier.abstracts;

public abstract class Utilisateur {
    protected int id ;
    protected String nom;
    protected String email ;

    protected Utilisateur() {};
    protected Utilisateur(String nom, String email) {

        this.nom = nom;
        this.email = email;
    }
    protected Utilisateur(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
