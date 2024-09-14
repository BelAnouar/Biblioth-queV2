package org.example.metier;

import org.example.metier.abstracts.Utilisateur;

public class Professeur extends Utilisateur {
    private String departemment;

    public Professeur() {
        super();
    }
    public Professeur( String nom, String email, String departemment) {

        super( nom, email);
        this.departemment = departemment;
    }
    public Professeur(int id, String nom, String email, String departemment) {

        super(id, nom, email);
        this.departemment = departemment;
    }
    public String getDepartemment() {
        return departemment;
    }

    public void setDepartemment(String departemment) {
        this.departemment = departemment;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "departemment='" + departemment + '\'' +
                ", email='" + email + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }


}
