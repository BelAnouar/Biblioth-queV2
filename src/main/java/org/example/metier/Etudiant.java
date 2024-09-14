package org.example.metier;

import org.example.metier.abstracts.Utilisateur;

public class Etudiant extends Utilisateur {
    private String numero;

    public Etudiant(){
        super();
    }
    public Etudiant( String nom, String email,String numero) {
        super(nom, email);
        this.numero = numero;
    }
    public Etudiant(int id, String nom, String email,String numero) {
        super(id, nom, email);
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "numero='" + numero + '\'' +
                ", nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
