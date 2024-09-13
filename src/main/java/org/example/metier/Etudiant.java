package org.example.metier;

import org.example.metier.abstracts.Utilisateur;

public class Etudiant extends Utilisateur {
    protected Etudiant(int id, String nom, String prenom) {
        super(id, nom, prenom);
    }
}
