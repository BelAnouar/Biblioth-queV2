package org.example.metier;

import org.example.metier.abstracts.Utilisateur;

public class Professeur extends Utilisateur {
    protected Professeur(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }
}
